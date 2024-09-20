package casper.components;

/**
 * A utility class for returning various messages as strings.
 */
public class Stringifier {
    /**
     * Returns a welcome message including the Casper ASCII art.
     *
     * @return The welcome message.
     */
    public static String getWelcomeMessage() {
        return "\nHello! I'm Casper!\nWhat can I do for you today?";
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param taskString The description of the task that was added.
     * @param taskCount  The current number of tasks in the task list.
     * @return The confirmation message.
     */
    public static String getAddedTaskMessage(String taskString, int taskCount) {
        return "Got it. I've added this task:\n"
                + taskString + "\n"
                + "Now you have " + taskCount + " task(s) in the list.";
    }

    /**
     * Returns the tasks that match the specified keyword.
     *
     * @param tasks   The string containing the tasks that match the keyword, formatted with task indices.
     *                If no tasks match, this string should be empty.
     * @param keyword The keyword used to search the tasks.
     * @return The matching tasks or a message if no tasks match.
     */
    public static String getFoundTasks(String tasks, String keyword) {
        if (tasks.equals("")) {
            return String.format("No task found that matches '%s'.", keyword);
        } else {
            return String.format("Here are the task(s) matching '%s':\n%s", keyword, tasks);
        }
    }
}
