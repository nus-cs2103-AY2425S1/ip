package joe.main;

import joe.commands.*;
import joe.exceptions.InvalidCommandException;
import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;
import joe.utils.Parser;

import java.sql.SQLOutput;

/**
 * Represents the user interface of the programme.
 */
public class Ui {
    private static final String LINE =
            "____________________________________________________________";
    private TaskList tasks;

    /**
     * Constructor for Ui.
     */
    public Ui() {
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
        try {
            return c.execute();
        } catch (InvalidIndexException e) {
            return e.getMessage();
        }
    }

    /**
     * Tells the reader that there is an error reading the saved file and will create a new empty task list.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e + "\n" + "Creating new task list.\n");
    }

    /**
     * Displays the welcome message when the program starts.
     */
    private static void showWelcome() {
        System.out.println(String.format("""
                %s
                Hello! I'm Joe
                What can I do for you?
                Type /help to see the list of available commands.
                %s
                """, LINE, LINE));
    }
}
