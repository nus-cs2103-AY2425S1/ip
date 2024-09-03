package fishman.utils;

import java.util.List;

import fishman.task.Task;
import fishman.task.TaskList;


/**
 * Represents the user interface for fishman bot.
 * This class handles all the inputs and outputs operations.
 */
public class Ui {

    private String response;

    /** Constructs a new Ui object and initializes the Scanner for user input. */
    public Ui() {
        this.response ="";
    }


    /**
     * Constructs a string with the confirmation message after a task is added.
     *
     * @param task The task object that is added.
     * @param size The size of the task list.
     * @return A string with the confirmation message and the updated task list size.
     */
    public String getAddedTaskMessage(Task task, int size) {
        return String.format("Bloop bloop, I have added the following task:\n%s\nNow you have %d tasks in the list.",
                task, size);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList object containing all tasks.
     */
    public String getTaskListMessage(TaskList tasks) {
        if (tasks.isEmpty()) {
            return ("Bloop bloop, no tasks found");
        }
        StringBuilder result = new StringBuilder("Bloop bloop, here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Displays the confirmation message after the task is marked or unmarked.
     *
     * @param task The task object that is marked or unmarked.
     */
    public String getTaskStatusMessage(Task task) {
        String statusMessage;
        if (task.getStatus()) {
            statusMessage = "Bloop bloop, I've marked this task as done:";
        } else {
            statusMessage = "Bloop bloop, I've marked this task as not done yet:";
        }
        return (statusMessage + "\n" + task.toString());
    }

    /**
     * Constructs a string with the confirmation message after a task is deleted.
     *
     * @param task The task object that is deleted.
     * @param size The size of the task list.
     * @return A string with the confirmation message and the updated task list size.
     */
    public String getDeletedTaskMessage(Task task, int size) {
        return String.format("Bloop bloop, I have removed the following task:\n%s\nNow you have %d tasks in the list.",
                task, size);
    }

    /**
     * Displays the results after find command.
     *
     * @param tasks The tasks that have been found.
     */
    public String getFindResultsMessage(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return ("Bloop bloop, no matching tasks found");
        }
        StringBuilder result = new StringBuilder("Bloop bloop, here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }


}
