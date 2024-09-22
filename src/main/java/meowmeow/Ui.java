package meowmeow;

//Solution below inspired by ChatGPT

/**
 * Represents the UI that provides messages based on the user input.
 */
public class Ui {

    /**
     * Returns the message for listing all tasks.
     *
     * @param tasks The list of tasks.
     * @return The message with the list of tasks.
     */
    public static String getTaskListMessage(TaskList tasks) {
        StringBuilder output = new StringBuilder("here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns the message for finding matching tasks.
     *
     * @param matchingTasks The list of tasks matching the search.
     * @return The message with the matching tasks.
     */
    public static String getFindTasksMessage(TaskList matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "no matching tasks found. \n";
        }

        StringBuilder output = new StringBuilder("here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns the message when marking a task as done.
     *
     * @param task The task that was marked done.
     * @return The message for marking the task.
     */
    public static String getMarkTaskDoneMessage(Task task) {
        return "nice!! :3 i've marked this task as done:\n  " + task + "\n";
    }

    /**
     * Returns the message when unmarking a task as not done.
     *
     * @param task The task that was unmarked.
     * @return The message for unmarking the task.
     */
    public static String getUnmarkTaskMessage(Task task) {
        return "meow!! i've marked this task as not done yet:\n  " + task + "\n";
    }

    /**
     * Returns the message when adding a ToDo task.
     *
     * @param todo  The ToDo task added.
     * @param size  The current number of tasks.
     * @return The message for adding the task.
     */
    public static String getAddTodoMessage(ToDo todo, int size) {
        return "meow! :3 i've added this task:\n  " + todo + "\nnow you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the message for an invalid deadline input.
     *
     * @return The invalid deadline message.
     */
    public static String getInvalidDeadlineMessage() {
        return "invalid deadline.\n pwease use this format: \n " +
                "deadline taskName /by yyyy-mm-dd \n";
    }

    /**
     * Returns the message when adding a Deadline task.
     *
     * @param deadline The Deadline task added.
     * @param size     The current number of tasks.
     * @return The message for adding the task.
     */
    public static String getAddDeadlineMessage(Deadline deadline, int size) {
        return "meow! :3 I've added this task:\n  " + deadline + "\nnow you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the message for an invalid event input.
     *
     * @return The invalid event message.
     */
    public static String getInvalidEventMessage() {
        return "invalid event.\n pwease use this format: \n" +
                "event taskName /from yyyy-mm-dd /to yyyy-mm-dd \n";
    }

    /**
     * Returns the message when adding an Event task.
     *
     * @param event The Event task added.
     * @param size  The current number of tasks.
     * @return The message for adding the task.
     */
    public static String getAddEventMessage(Event event, int size) {
        return "meow! :3 i've added this task:\n  " + event + "\nnow you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the message for an invalid Do Within input.
     *
     * @return The invalid Do Within message.
     */
    public static String getInvalidDoWithinMessage() {
        return "invalid doWithin.\n pwease use this format: \n" +
                "doWithin taskName /between yyyy-mm-dd /and yyyy-mm-dd \n";
    }

    /**
     * Returns the message when adding an Do Within task.
     *
     * @param doWithin The do within task added.
     * @param size  The current number of tasks.
     * @return The message for adding the task.
     */
    public static String getAddDoWithinMessage(DoWithin doWithin, int size) {
        return "meow! :3 I've added this task:\n  " + doWithin + "\nNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the message when deleting a task.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks.
     * @return The message for deleting the task.
     */
    public static String getDeleteTaskMessage(Task task, int size) {
        return "Noted. >:( I've removed this task:\n  " + task + "\nNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the message for an invalid task number.
     *
     * @return The invalid task number message.
     */
    public static String getInvalidTaskNumberMessage() {

        return "invalid task number.\n";
    }

    /**
     * Returns the goodbye message when the user exits the program.
     *
     * @return The goodbye message.
     */
    public static String getGoodbyeMessage() {

        return "bye!! hope to see you again soon! :3 \n";
    }

    /**
     * Returns the message for an unknown or invalid command.
     *
     * @return The message indicating an unknown command.
     */
    public static String getUnknownCommandMessage() {

        return "sorry, i don't know what that means...\n";
    }
}
