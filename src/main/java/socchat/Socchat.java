package socchat;

import java.util.Scanner;

/**
 * Represents the main class for the Socchat application.
 * This class handles user interaction, manages tasks, and processes commands.
 */
public class Socchat {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Enum representing all available commands in the Socchat application.
     */
    enum Command{
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, FIND
    }

    /**
     * Constructs a Socchat instance with the specified file path.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Socchat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(Storage.load());
        } catch (SocchatException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Starts the Socchat application and enters the main command loop.
     * Processes user input and executes commands based on the input.
     */
    public void run() {
        greet();

        chatLoop:
        while (true) {
            String[] strToken;
            try{
                String input = scanner.next();
                Command command = getCommand(input);
                System.out.print("> ");
                switch (command) {
                case BYE:
                    exit();
                    break chatLoop;
                case LIST:
                    taskList.list();
                    break;
                case MARK:
                    String taskIndexString = scanner.nextLine().trim();
                    taskList.setMark(taskIndexString,true);
                    break;
                case UNMARK:
                    taskIndexString = scanner.nextLine().trim();
                    taskList.setMark(taskIndexString, false);
                    break;
                case TODO:
                    String str = command + scanner.nextLine();
                    strToken = Parser.tokenizeAdd(str);
                    taskList.addTodo(strToken);
                    break;
                case DEADLINE:
                    str = command + scanner.nextLine();
                    strToken = Parser.tokenizeAdd(str);
                    taskList.addDeadline(strToken);
                    break;
                case EVENT:
                    str = command + scanner.nextLine();
                    strToken = Parser.tokenizeAdd(str);
                    taskList.addEvent(strToken);
                    break;
                case DELETE:
                    taskIndexString = scanner.nextLine().trim();
                    taskList.delete(taskIndexString);
                    break;
                case FIND:
                    str = scanner.nextLine().trim();
                    taskList.find(str);
                    break;
                }
            } catch (SocchatException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Socchat("tasks.txt").run();
    }

    /**
     * Converts the input string to a Command enum value.
     * Throws an exception if the input does not match any command.
     *
     * @param input the command string input from the user
     * @return the corresponding Command enum value
     * @throws SocchatException if the input does not match a valid command
     */
    public static Command getCommand(String input) throws SocchatException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SocchatException("Uh Ohh! Socchat does not understand this...");
        }

    }

    /**
     * Prints a greeting message to the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }



}

