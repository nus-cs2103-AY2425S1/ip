package knight2103;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;

/**
 * User interface of the bot. Primarily contains methods of the various messages the bot will show in the
 * interface.
 */
public class Ui {
    /**
     * Shows the message of the bot. This message will appear the first time the GUI is initiated,
     * where the bot will send a response first.
     * @param message Message for the bot to initiate.
     * @return The message the bot initiates.
     */
    public String showWelcome(String message) {
        return message;
    }

    /**
     * Shows the message of the bot after AddCommand execution (task is added).
     * @param taskToAdd The task to be added to the bot's list of task.
     * @param tasks The object storing the list of tasks found in the bot.
     * @return The message after AddCommand execution.
     */
    public String showAdd(Task taskToAdd, TaskList tasks) {
        return "Task added:\n" + taskToAdd + "\nTotal number of tasks in list: "
                + tasks.getSize() + "\nType command \"list\" to see full list of tasks.";
    }

    /**
     * Shows the message of the bot after task is being marked.
     * @param taskAffected The task to be marked as done.
     * @return The message after task is being marked.
     */
    public String showMark(Task taskAffected) {
        return "Mark this task as done!:\n" + taskAffected
                + "\nType command \"list\" to see updated list of tasks.";
    }

    /**
     * Shows the message of the bot after task is being unmarked (mark as not done).
     * @param taskAffected The task to be marked as not done.
     * @return The message after task is being marked as not done.
     */
    public String showUnmark(Task taskAffected) {
        return "Mark this task as not done yet!:\n" + taskAffected
                + "\nType command \"list\" to see updated list of tasks.";
    }

    /**
     * Shows the message of the bot after task is being deleted.
     * @param taskAffected The task to be deleted.
     * @return The message after task is being deleted.
     */
    public String showDelete(Task taskAffected, TaskList tasks) {
        return "Task removed:\n" + taskAffected + "\nTotal number of tasks in list: "
                + tasks.getSize() + "\nType command \"list\" to see full list of tasks.";
    }

    /**
     * Shows the message of the bot which is the full list of tasks the bot has.
     * @param listToShow The list of tasks to be shown in the GUI in the correct formatting style.
     * @return The message including the full list of tasks.
     */
    public String showList(String listToShow) {
        return "Here's the list of tasks:\n" + listToShow;
    }

    /**
     * Shows the message of the bot after finding tasks that have their description
     * matched with the key word. The message includes the list of matched tasks.
     * @param listToShow The list of matched tasks to be shown in the GUI in the correct formatting style.
     * @return The message including the list of tasks that matches the search word.
     */
    public String showFind(String listToShow) {
        return listToShow.isEmpty()
                ? "NIL: There is no matching tasks.\n"
                : "Here are the matching tasks in your list:\n" + listToShow;
    }

    /**
     * Shows the message of the bot containing the sorted list after the list of tasks are sorted.
     * @param listToShow The sorted list of tasks to be shown in the GUI in the correct formatting style.
     * @return The message including the sorted list of tasks.
     */
    public String showSorted(String listToShow) {
        return "Here's the sorted list of tasks:\n" + listToShow;
    }

    /**
     * Shows the message of the bot after ByeCommand execution, where the bot ceases its operations
     * (The GUI programme ends).
     * @return The message after the bot ceases its operations.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}