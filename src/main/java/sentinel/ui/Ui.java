package sentinel.ui;

import java.util.Scanner;
import sentinel.Sentinel;
import sentinel.task.Task;
import sentinel.utils.SentinelList;

/**
 * Provides the user interface for the Sentinel application.
 * This class handles all interactions with the user, including displaying messages,
 * reading user input, and providing guidance on how to use the application's features.
 */
public class Ui {
    private final Scanner sc;
    private final static String logo =
            """
                   _____                                                                                      _____\s
                  ( ___ )                                                                                    ( ___ )
                   |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |\s
                   |   |                                                                                      |   |\s
                   |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   |\s
                   |   |  |\\   ____\\|\\  ___ \\ |\\   ___  \\|\\___   ___\\\\  \\|\\   ___  \\|\\  ___ \\ |\\  \\           |   |\s
                   |   |  \\ \\  \\___|\\ \\   __/|\\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\          |   |\s
                   |   |   \\ \\_____  \\ \\  \\_|/_\\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\  \\         |   |\s
                   |   |    \\|____|\\  \\ \\  \\_|\\ \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\____    |   |\s
                   |   |      ____\\_\\  \\ \\_______\\ \\__\\  \\__\\   \\ \\__\\ \\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\  |   |\s
                   |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|_______|\\|_______|   |   |\s
                   |   |     \\|_________|                                                                     |   |\s
                   |   |                                                                                      |   |\s
                   |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\s
                  (_____)                                                                                    (_____)""";

    private final static String helpText = """
                    1. todo <task>                                Adds tasks without any date/time attached to list.
                    2. deadline <task> /by <date>                 Adds tasks that need to be done before a specific date/time to list.
                    3. event <event> /from <date> /to <date>      Adds tasks that start at a specific date/time and ends at a specific date/time to list.
                    4. list                                       List all tasks.
                    5. mark <index>                               Mark task as done.
                    6. unmark <index>                             Mark task as undone.
                    7. delete <index>                             Deletes task.
                    8. bye                                        Ends the chatbot.
                    """;

    private final static String line = "____________________________________________________________\n";

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message and application logo.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("\nWhat can I do for you?");
        showLine();
    }

    /**
     * Prints a horizontal line for visual separation in the UI.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The trimmed line of input from the user.
     */
    public String readLine() {
        return sc.nextLine().trim();
    }

    /**
     * Displays an error message when an exception is thrown.
     *
     * @param e The exception to display.
     */
    public void showError(Exception e) {
        System.err.println("Sentinel just experienced an error! " + e.getMessage());
        showLine();
    }

    /**
     * Displays a goodbye message when the application ends.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays help information for the user, listing all available commands and their descriptions.
     */
    public void showHelp() {
        System.out.println(helpText);
    }

    /**
     * Displays guidelines for using the 'event' command.
     */
    public void showEventCommandGuidelines() {
        System.out.println("Please state the start and end date using /from <date> and /to <date> respectively (e.g., event project meeting /from 30 Aug 2024 2pm /to 30 Aug 2024 4pm)");
    }

    /**
     * Displays an error message when the start date is after the end date for an event.
     */
    public void showEventDateOrder() {
        System.out.println("Start Date cannot be after End Date. Please try again.");
    }

    /**
     * Displays guidelines for using the 'deadline' command.
     */
    public void showDeadlineCommandGuidelines() {
        System.out.println("Please state the deadline using /by <date> (e.g., deadline return book /by 30 Aug 2024 5pm)");
    }

    /**
     * Displays guidelines for modifying tasks (marking, unmarking, deleting).
     */
    public void showModifyGuidelines() {
        System.out.println("Please state a valid index you wish to mark/unmark/delete with the command (e.g.: mark 1)");
    }

    /**
     * Displays an error message when an invalid index is provided.
     */
    public void showNoItemExists() {
        System.out.println("No such item in the list!");
    }

    /**
     * Displays an error message when the task name is empty.
     *
     * @param commandType The type of command that requires a task name.
     */
    public void showEmptyTaskNameError(Sentinel.CommandType commandType) {
        System.out.println(commandType.name().substring(0, 1).toUpperCase() + commandType.name().substring(1) + " name cannot be empty");
    }

    /**
     * Displays an error message for unrecognized commands.
     */
    public void showUnrecognisedCommand() {
        System.out.println("Unrecognised Sentinel command. Type \"help\" to list all commands.");
    }

    /**
     * Displays the status and details of a task after it has been marked or unmarked.
     *
     * @param t The task that was marked or unmarked.
     */
    public void showTaskMark(Task t) {
        System.out.println("Alright! I've marked this task as " + (t.isDone() ? "done" : "undone") + ":");
        System.out.println("\t" + t.getStatusIcon() + " " + t);
    }

    /**
     * Displays the list of tasks.
     *
     * @param lst The list of tasks to display.
     */
    public void showList(SentinelList lst) {
        System.out.println("Here " + (lst.isSizeOne() ? "is" : "are") + " the " + (lst.isSizeOne() ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.getListedString(i));
        }
    }

    /**
     * Displays the filtered list of tasks.
     *
     * @param lst The list of tasks to filter from.
     */
    public void showFileredList(SentinelList lst, String keyword) {
        lst = lst.filter(keyword);
        System.out.println("Here " + (lst.sizeOne() ? "is" : "are") + " the " + (lst.sizeOne() ? "task" : "tasks") +
                " in your list that " + (lst.sizeOne() ? "contains" : "contain") + " the keyword \"" + keyword + "\":");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.getListedString(i));
        }
    }

    /**
     * Displays a message when a task is removed and shows the number of remaining tasks.
     *
     * @param list   The updated list of tasks.
     * @param removed The task that was removed.
     */
    public void showRemovedAndRemaining(SentinelList list, Task removed) {
        System.out.println("I have deleted the following task:\n\t" + removed.listedString() +
                "\nYou have " + list.size() + " remaining " + (list.isSizeOne() ? "task" : "tasks") + ".");
    }

    /**
     * Displays a message when a task is already marked as done or undone.
     *
     * @param t The task that is already marked.
     */
    public void showAlreadyMarked(Task t) {
        if (t.isDone()) {
            System.out.println(t + " has already been marked as done.");
        } else {
            System.out.println(t + " has already been marked as undone.");
        }
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param t The task that was added.
     */
    public void showAddedTask(Task t) {
        System.out.println("Got it. I've added this task: " + t);
        System.out.println("\t" + t.listedString());
    }
}
