package sentinel.utils;

import java.util.stream.Collectors;

import sentinel.Sentinel;
import sentinel.task.Task;

/**
 * Provides the user interface messages for the Sentinel application.
 * This class handles all output interactions with the user.
 */
public class SentinelString {
    private static final String logo =
            "       _____                                                                                      _____\n"
        + "      ( ___ )                                                                                    ( ___ )\n"
        + "       |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |\n"
        + "       |   |                                                                                      |   |\n"
        + "       |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   |\n"
        + "       |   |  |\\   ____\\|\\  ___ \\ |\\   ___  \\|\\___   ___\\\\  \\|\\   ___  \\|\\  ___ \\ |\\  \\   "
                    + "        |   |\n"
        + "       |   |  \\ \\  \\___|\\ \\   __/|\\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\"
                    + " \\  \\          |   |\n"
        + "       |   |   \\ \\_____  \\ \\  \\_|/_\\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/"
                    + "_\\ \\  \\         |   |\n"
        + "       |   |    \\|____|\\  \\ \\  \\_|\\ \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_"
                    + "|\\ \\ \\  \\____    |   |\n"
        + "       |   |      ____\\_\\  \\ \\_______\\ \\__\\  \\__\\   \\ \\__\\ \\ \\__\\ \\__\\\\ \\__\\ \\_______"
                    + "\\ \\_______\\  |   |\n"
        + "       |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|__|\\________|\\|_______|"
                    + "  |   |\n"
        + "       |   |     \\|_________|                                                                     |   |\n"
        + "       |   |                                                                                      |   |\n"
        + "       |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\n"
        + "      (_____)                                                                                    (_____)\n";


    private static final String helpText = """
            1. todo <task>                                Adds tasks without any date/time attached to list.
            2. deadline <task> /by <date>                 Adds tasks that need to be done before a specific
                                                          date/time to list.
            3. event <event> /from <date> /to <date>      Adds tasks that start at a specific date/time and
                                                          ends at a specific date/time to list.
            4. list                                       List all tasks.
            5. mark <index>                               Mark task as done.
            6. unmark <index>                             Mark task as undone.
            7. delete <index>                             Deletes task.
            8. bye                                        Ends the chatbot.
            """;

    private static final String line = "____________________________________________________________\n";


    /**
     * Constructs a new SentinelString instance.
     */
    public SentinelString() {
    }

    /**
     * Returns a welcome message and application logo.
     */
    public static String stringWelcome() {
        return "Hello from\n" + logo + "\nWhat can I do for you?\n" + line;
    }

    /**
     * Returns a horizontal line for visual separation in the UI.
     */
    public static String stringLine() {
        return line;
    }

    /**
     * Returns an error message when an exception is thrown.
     *
     * @param e The exception to display.
     * @return Error message with exception details.
     */
    public static String stringError(Exception e) {
        return "Sentinel just experienced an error! " + e.getMessage() + "\n" + line;
    }

    /**
     * Returns a goodbye message when the application ends.
     */
    public static String stringGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns help information for the user, listing all available commands and their descriptions.
     */
    public static String stringHelp() {
        return helpText;
    }

    /**
     * Returns guidelines for using the 'event' command.
     */
    public static String stringEventCommandGuidelines() {
        return "Please state the start and end date using /from <date> and /to <date> respectively "
                + "(e.g., event project meeting /from 30 Aug 2024 2pm /to 30 Aug 2024 4pm)";
    }

    /**
     * Returns an error message when the start date is after the end date for an event.
     */
    public static String stringEventDateOrder() {
        return "Start Date cannot be after End Date. Please try again.";
    }

    /**
     * Returns guidelines for using the 'deadline' command.
     */
    public static String stringDeadlineCommandGuidelines() {
        return "Please state the deadline using /by <date> "
                + "(e.g., deadline return book /by 30 Aug 2024 5pm)";
    }

    /**
     * Returns guidelines for modifying tasks (marking, unmarking, deleting).
     */
    public static String stringModifyGuidelines() {
        return "Please state a valid index you wish to mark/unmark/delete with the command (e.g.: mark 1)";
    }

    /**
     * Returns an error message when an invalid index is provided.
     */
    public static String stringNoItemExists() {
        return "No such item in the list!";
    }

    /**
     * Returns an error message when the task name is empty.
     *
     * @param commandType The type of command that requires a task name.
     * @return An error message stating the task name cannot be empty.
     */
    public static String stringEmptyTaskNameError(Sentinel.CommandType commandType) {
        return commandType.name().substring(0, 1).toUpperCase()
                + commandType.name().substring(1) + " name cannot be empty";
    }

    /**
     * Returns an error message for unrecognized commands.
     */
    public static String stringUnrecognisedCommand() {
        return "Unrecognised Sentinel command. Type \"help\" to list all commands.";
    }

    /**
     * Returns the status and details of a task after it has been marked or unmarked.
     *
     * @param t The task that was marked or unmarked.
     * @return Message indicating if the task is marked or unmarked.
     */
    public static String stringTaskMark(Task t) {
        return "Alright! I've marked this task as " + (t.isDone() ? "done" : "undone") + ":\n\t"
                + t.getStatusIcon() + " " + t;
    }

    /**
     * Returns the list of tasks.
     *
     * @param lst The list of tasks to display.
     * @return A formatted string of tasks in the list.
     */
    public static String stringList(SentinelList lst) {
        String header = "Here " + (lst.isSizeOne() ? "is" : "are") + " the "
                + (lst.isSizeOne() ? "task" : "tasks") + " in your list:\n";

        return lst.stream()
                .map(t -> "\t" + (lst.indexOf(t) + 1) + "." + lst.getListedString(lst.indexOf(t)))
                .collect(Collectors.joining("\n", header, "\n"));
    }


    /**
     * Returns the filtered list of tasks.
     *
     * @param lst The list of tasks to filter from.
     * @return A formatted string of filtered tasks.
     */
    public static String stringFilteredList(SentinelList lst, String keyword) {
        SentinelList filteredList = lst.filter(keyword);
        String header = "Here " + (filteredList.isSizeOne() ? "is" : "are") + " the "
                + (filteredList.isSizeOne() ? "task" : "tasks") + " in your list that "
                + (filteredList.isSizeOne() ? "contains" : "contain") + " the keyword \"" + keyword + "\":\n";

        return filteredList.stream()
                .map(t -> "\t" + (filteredList.indexOf(t) + 1)
                        + "." + filteredList.getListedString(filteredList.indexOf(t)))
                .collect(Collectors.joining("\n", header, "\n"));
    }


    /**
     * Returns a message when a task is removed and returns the number of remaining tasks.
     *
     * @param list    The updated list of tasks.
     * @param removed The task that was removed.
     */
    public static String stringRemovedAndRemaining(SentinelList list, Task removed) {
        return "I have deleted the following task:\n\t" + removed.listedString()
                + "\nYou have " + list.size() + " remaining " + (list.isSizeOne() ? "task" : "tasks") + ".";
    }

    /**
     * Returns a message when a task is already marked as done or undone.
     *
     * @param t The task that is already marked.
     * @return A message indicating the task is already marked.
     */
    public static String stringAlreadyMarked(Task t) {
        if (t.isDone()) {
            return t + " has already been marked as done.";
        } else {
            return t + " has already been marked as undone.";
        }
    }

    /**
     * Returns a message when a new task is added.
     *
     * @param t The task that was added.
     * @return A message indicating the task was added.
     */
    public static String stringAddedTask(Task t) {
        return "Got it. I've added this task: " + t + "\n\t" + t.listedString();
    }
}
