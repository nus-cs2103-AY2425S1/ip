package joe.main;

import java.util.Scanner;

import joe.commands.*;
import joe.exceptions.InvalidCommandException;
import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;
import joe.utils.Parser;

/**
 * Represents the user interface of the programme.
 */
public class Ui {
    private static final String LINE =
            "____________________________________________________________";
//    private final Scanner reader;
    private TaskList tasks;
    private Command previousCommand;

    /**
     * Constructor for Ui.
     */
    public Ui() {
//        reader = new Scanner(System.in);
        showWelcome();
    }

    /**
     * Sets the task list.
     * @param tasks the task list
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Responds to the user's command.
     *
     * @param userCmd the user's command
     */
    public String responseToCommand(String userCmd) {
        Parser parser = new Parser(tasks);
        Command c;
        switch (userCmd) {
        case "/help":
            c = new HelpCommand();
            break;
        case "bye":
            c = new ByeCommand();
            break;
        case "list":
            c = new ListCommand(tasks);
            break;
        case "save":
            c = new SaveCommand(tasks);
            break;
        default:
            try {
                c = parser.parseCommand(userCmd);
            } catch (IllegalArgumentException | InvalidIndexException | InvalidCommandException e) {
                return e.getMessage();
            }
        }
        previousCommand = c;
        return c.execute();
    }

    /**
     * Tells the reader that there is an error reading the saved file and will create a new empty task list.
     */
    public String showLoadingError(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e).append("\n").append("Creating new task list.\n");
        return sb.toString();
    }

    /**
     * Displays the welcome message when the program starts.
     */
    private static void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Joe\nWhat can I do for you?\nType /help to see the list of available commands.");
        System.out.println(LINE);
    }
}
