package taskalyn;

/**
 * Manages the user interactions.
 */
public class Ui {

    /**
     * Prints the given input within two horizontal lines (Unused for GUI).
     *
     * @param input Any message from the bot.
     */
    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Returns the starting welcome message from Taskalyn.
     */
    public String showWelcome() {
        return "Hey! I'm Taskalyn, your personal Task Manager :)\n"
                + "    What can I do for you?";
    }

    /**
     * Returns the add task message from Taskalyn.
     *
     * @param task The task which was added.
     * @param taskManager The taskManager object.
     * @return A String saying that the task was added.
     */
    public String showAddTaskMessage(Task task, TaskManager taskManager) {
        int numberofTasks = taskManager.getTaskSize();
        return "Got it, I've added this task to your list!\n"
                + "      " + task.toString() + "\n" + "    Wah bro... "
                + numberofTasks + (numberofTasks > 1 ? " tasks already!" : " task already!");
    }

    /**
     * Returns the delete task message from Taskalyn.
     *
     * @param task The task which was deleted.
     * @param taskManager The taskManager object.
     * @return A String saying that the task was deleted.
     */
    public String showDeleteTaskMessage(Task task, TaskManager taskManager) {
        int numberofTasks = taskManager.getTaskSize();
        return "Awesome bro! One task gone :D\n"
                + "      " + task.toString() + "\n" + "    Wah bro... "
                + numberofTasks + (numberofTasks > 1 ? " tasks already!" : " task already!");
    }

    /**
     * Returns the mark task as complete message from Taskalyn.
     *
     * @param task The task which was marked as complete.
     * @return A String saying that the task was marked as complete.
     */
    public String showMarkTaskAsCompleteMessage(Task task) {
        return "Nice, I've marked this task as complete:\n"
                + "       " + task.toString();
    }

    /**
     * Returns the mark task as incomplete message from Taskalyn.
     *
     * @param task The task which was marked as incomplete.
     * @return A String saying that the task was marked as incomplete.
     */
    public String showMarkTaskAsIncompleteMessage(Task task) {
        return "Ok, I've marked this task as incomplete:\n"
                + "       " + task.toString();
    }

    /**
     * Returns the result of the searching of tasks by keyword.
     *
     * @param message The message to be returned.
     * @return A String of the message to be returned.
     */
    public String showSearchTasksByKeywordMessage(String message) {
        return message;
    }

    public String showByeMessage() {
        return "Bye! Hope to see you again soon!";
    }
}
