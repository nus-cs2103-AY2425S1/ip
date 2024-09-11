package susan.ui;

import susan.task.Task;
import susan.task.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public Ui() {}

    /**
     * Greets the user.
     */
    public String showWelcome() {
        return """
                Hi there, I'm Susan!
                How may I help you be productive today?
                """;
    }

    /**
     * Prints farewell message when the user says 'bye'.
     */
    public String showGoodbye() {
        return "Good bye, slay the day!\n";
    }

    /**
     * Prints information about the error encountered by the user.
     */
    public String showError(String message) {
        return "NOT OK. " + message;
    }

    /**
     * Displays the current task list to the user.
     * @param tasks updated TaskList.
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.size() > 0) {
            return " Here are the tasks in your list:\n"
                    + tasks.printList();
        }
        return "There are no tasks in your list...";
    }

    public String showAddTask(Task task, int size) {
        return " Got it. I've added this task:\n"
            + "   " + task + "\n"
            + " You have " + size + " task(s) in the list.";
    }

    /**
     * Informs the user that the task has been deleted.
     * @param task Task that user wants to delete.
     * @param size size of current TaskList.
     */
    public String showDeleteTask(Task task, int size) {
        return " Noted. I've removed this task:\n"
            + "   " + task + "\n"
            + " You have " + size + " task(s) in the list.";
    }

    /**
     * Informs the user that the task has been marked as done or undone.
     * @param task Task that user wants to mark/unmark.
     * @param size size of current TaskList.
     */
    public String showMarkTask(Task task, int size) {
        return " Alright! I've un/marked this task:\n"
            + "   " + task + "\n"
            + " You have " + size + " task(s) in the list.";
    }

    public String showMatchingTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Sorry! No tasks found matching your keyword.";
        } else {
            return "Here are the matching tasks in your list:\n"
                + tasks.printList();
        }
    }

    public String showReminders(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Woohoo! No work for the next 3 days!";
        }
        return "The following tasks are due soon!\n"
            + tasks.printList();
    }
}
