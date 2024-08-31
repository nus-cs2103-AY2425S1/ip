package carly;

import java.io.IOException;
import java.util.Scanner;

import carly.exception.*;
import carly.ui.Ui;
import carly.utils.Parser;
import carly.utils.Storage;
import carly.utils.TaskList;

/**
 *  Represents a chatbot named Carly that manages a list of tasks.
 *  Can interact with the chatbot using Commands.
 */
public class Carly {

    /** Manages the list of tasks for the chatbot. */
    private final TaskList taskList;

    public Carly() {
        this.taskList = new TaskList();
    }

    /**
     * Initiates a conversation with the user. Can use commands to do various tasks.
     * The conversation continues until the user inputs the "BYE" command.
     *
     * If the user enters an invalid command or provides insufficient information
     * (e.g., missing task description), appropriate exceptions are caught, and
     * error messages are displayed without terminating the chat.
     */
    private void chat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String username = scan.nextLine();

        String input;
        String taskDescription;
        Parser.Command command;
        Ui ui = new Ui(username);
        ui.welcomeMsg();

        Storage listStorage = new Storage("./data/CarlyList.txt");

        while (true) {
            input = ui.ReadInput();
            Parser parser = new Parser(input);

            try {
                command = parser.getCommand();
                taskDescription = parser.getDetailsAfterCommand(command);
            } catch (CarlyException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (command) {
            case BYE:
                ui.byeMsg();
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
            } catch (IOException | CarlyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /** Creates a new instance of the Carly chatbot and starts the chat session. */
    public static void main(String[] args) {
        Carly carly = new Carly();
        try {
            carly.chat();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

