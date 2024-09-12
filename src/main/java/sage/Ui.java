package sage;

import sage.Task.Task;

/**
 * Represents the user interface for the Sage application.
 * This class handles user interactions.
 */
public class Ui {
    private static final String NAME = "sage";

    public String showWelcome() {
        return String.format("Hello! I'm %s\nWhat can i do for you?", NAME);
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats a message to indicate that a task has been added to the list.
     *
     * @param addedTask The task that has been added.
     * @param size      The current number of tasks in the list after adding the new task.
     * @return A formatted string message indicating the task has been added and the updated task count.
     */
    public String showAddedTask(Task addedTask, int size) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", addedTask, size);
    }

    /**
     * Formats a message to indicate that a task has been removed from the list.
     *
     * @param deletedTask The task that has been removed.
     * @param size        The current number of tasks in the list after removing the task.
     * @return A formatted string message indicating the task has been removed and the updated task count.
     */
    public String showDeletedTask(Task deletedTask, int size) {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", deletedTask, size);
    }

    public String showError(String errorMessage) {
        return String.format("ERROR: " + errorMessage);
    }
}
