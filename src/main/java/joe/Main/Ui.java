package joe.Main;

import joe.commands.*;
import joe.exceptions.InvalidCommandException;
import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String line =
            "____________________________________________________________";
    private final Scanner reader;
    private TaskList tasks;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        reader = new Scanner(System.in);
        showWelcome();
    }

    /**
     * Runs the programme.
     *
     * @param tasks the TaskList object containing the tasks
     */
    public void runProgramme(TaskList tasks) {
        this.tasks = tasks;
        responseToCommand(receiveCommand());
    }

    /**
     * Displays the message when the user exits the program.
     */
    public String receiveCommand() {
        return reader.nextLine().strip();
    }

    /**
     * Responds to the user's command.
     *
     * @param userCmd the user's command
     */
    public void responseToCommand(String userCmd) {
        Parser parser = new Parser(tasks);
        boolean isExit = false;
        Command c = null;
        while (c == null || !c.isBye()) {
            System.out.println(line);
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
                    System.out.println(e.getMessage());
                    System.out.println(line);
                    continue;
                }
            }
            c.execute();
            System.out.println(line);

            if (!c.isBye()) {
                userCmd = receiveCommand();
            }
        }
    }

    /**
     * Tells the reader that there is an error reading the saved file and will create a new empty task list.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e);
        System.out.println("Creating new task list.");
    }

    /**
     * Displays the welcome message when the program starts.
     */
    private static void showWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm Joe\nWhat can I do for you?\nType /help to see the list of available commands.");
        System.out.println(line);
    }
}
