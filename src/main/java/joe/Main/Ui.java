package joe.Main;

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
        while (!isExit) {
            switch (userCmd) {
            case "/help":
                sendHelp();
                break;
            case "bye":
                sayGoodbye();
                isExit = true;
                break;
            case "list":
                tasks.listTasks();
                break;
            case "save":
                tasks.saveTasks();
                break;
            default:
                try {
                    parser.parseCommand(userCmd);
                } catch (IllegalArgumentException | InvalidIndexException | InvalidCommandException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            }
            if (!isExit) {
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

    /**
     * Prints a goodbye message.
     */
    private static void sayGoodbye() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    /**
     * Prints a list of available commands.
     */
    private static void sendHelp() {
        System.out.println(line);
        System.out.println("bye: ends our interaction :-(");
        System.out.println("deadline <description> /by <due date/time>: Creates a Deadline task");
        System.out.println("delete <idx>: Deletes the task at your chosen index");
        System.out.println("event <description> /from <start date/time> /to <end date/time>: Creates an Event task");
        System.out.println("find <queryString>: Finds task with descriptions matching your query regex");
        System.out.println("list: Displays your current tasks");
        System.out.println("mark <idx>: Marks the task at your chosen index");
        System.out.println("save : Saves all tasks in your current list to the database that will be automatically " +
                "loaded during your next session");
        System.out.println("todo <description>: Creates a ToDo task");
        System.out.println("unmark <idx>: Unmarks the task at your chosen index");
        System.out.println(line);
    }
}
