package carly;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import carly.exception.*;
import carly.exception.CarlyNoTaskDescription;
import carly.tasks.TaskList;

/**
 *  Represents a chatbot named Carly that manages a list of tasks.
 *  Can interact with the chatbot using Commands.
 */
public class Carly {

    /** File path used to store list of tasks. */
    private final String FILE_PATH = "./data/CarlyList.txt";

    /** Manages the list of tasks for the chatbot. */
    private final TaskList taskList;

    /** The name of the user interacting with the Carly chatbot. */
    private String username;

    /** Represents the set of commands that the chatbot can recognize and process. */
    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public Carly() {
        this.taskList = new TaskList();
        this.username = "";
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /** Sets the user's input name as the username of the chat. */
    private void updateUsername(String username) {
        this.username = username;
    }

    /** Displays a welcome message to the user with the current username. */
    private void welcomeMsg() {
        System.out.println("Hey " + this.username + "! I'm Carly.\nWhat can I do for you?");
    }

    /** Displays a farewell message to the user with the current username. */
    private void byeMsg() {
        System.out.println("Bye " + this.username + ". I'll see you next time!");
    }

    /**
     * Converts the user's input action string to a corresponding enum value.
     *
     * @param action the user's input action string.
     * @return the corresponding {@link Command} enum value.
     * @throws CarlyUnknownIInputException if the input action does not match any known command.
     */
    private Command getCommand(String action) throws CarlyUnknownIInputException {
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CarlyUnknownIInputException(action);
        }
    }

    /**
     * Extracts the task description from the user's input after the command.
     *
     * @param command         the input command in Command enum.
     * @param input           the entire user input string.
     * @param firstSpaceIndex the index of the first space character in the input string.
     * @return the task description without the command type.
     * @throws CarlyNoTaskDescription if the command requires a task description but none is provided.
     */
    private String getDetailsAfterCommand(Command command, String input, Integer firstSpaceIndex) throws CarlyNoTaskDescription {
        Command[] noDescriptionCommands = {Command.BYE, Command.LIST};
        boolean requiresDescription = !Arrays.asList(noDescriptionCommands).contains(command);

        if (requiresDescription) {
            String taskDescription = input.substring(firstSpaceIndex + 1).trim();
            if ((!input.contains(" ")) || (taskDescription.isEmpty())) {
                throw new CarlyNoTaskDescription();
            }
            return taskDescription;
        }
        return input.trim();
    }

    /**
     * Initiates a conversation with the user, prompting for their name and
     * allowing them to manage their tasks using various commands.
     * The conversation continues until the user inputs the "BYE" command.
     *
     * Starts a conversation with the user, asking for their name and allowing them
     * to manage their tasks using various commands in {@link Command}.
     *
     * If the user enters an invalid command or provides insufficient information
     * (e.g., missing task description), appropriate exceptions are caught, and
     * error messages are displayed without terminating the chat.
     */
    private void chat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String username = scan.nextLine();
        updateUsername(username);

        String input;
        String taskDescription;
        Command command;
        welcomeMsg();
        Storage listStorage = new Storage(FILE_PATH);

        while (true) {
            if (scan.hasNextLine()) {
                input = scan.nextLine();
            } else {
                System.out.println("No input detected. Exiting...");
                break;
            }

            int firstSpaceIndex = input.indexOf(" ");
            String[] parts = input.split(" ");
            String action = parts[0];

            try {
                command = getCommand(action);
                taskDescription = this.getDetailsAfterCommand(command, input, firstSpaceIndex);
            } catch (CarlyException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (command) {
            case BYE:
                byeMsg();
                return;
            case LIST:
                this.taskList.printTaskList();
                break;
            case MARK:
                try {
                    this.taskList.mark(taskDescription);
                } catch (CarlyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case UNMARK:
                try {
                    this.taskList.unmark(taskDescription);
                } catch (CarlyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DELETE:
                try {
                    this.taskList.delete(taskDescription);
                    break;
                } catch (CarlyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case TODO:
                try {
                    this.taskList.addToDo(taskDescription);
                } catch (CarlyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    this.taskList.addDeadLine(taskDescription);
                } catch (CarlyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    this.taskList.addEvent(taskDescription);
                } catch (CarlyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            try {
                listStorage.savesFile(this.taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the program.
     * Creates a new instance of the Carly chatbot and starts the chat session.
     * Any exceptions thrown during the chat are caught and printed to the console.
     */
    public static void main(String[] args) {
        Carly carly = new Carly();
        try {
            carly.chat();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

