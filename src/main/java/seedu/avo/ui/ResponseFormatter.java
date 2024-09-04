package seedu.avo.ui;

import java.util.List;

import seedu.avo.tasks.Task;

/**
 * Represents an interface for user interactions
 */
public class ResponseFormatter {
    private String getTask(List<Task> tasks, int index) {
        return index + 1 + ". " + tasks.get(index);
    }
    /**
     * Displays the number of tasks with a user-friendly message
     * @param count The number of tasks
     */
    public String showTaskCount(int count) {
        if (count == 0) {
            return "You have no tasks.";
        } else if (count == 1) {
            return "You have one task.";
        } else {
            return String.format("You have %s tasks.", count);
        }
    }

    /**
     * Displays the list of tasks with the given indices in a user-friendly manner
     * @param tasks The list of tasks
     * @param indexes The list of indices that will be printed out
     */
    public String showTasksFromList(List<Task> tasks, List<Integer> indexes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer index: indexes) {
            stringBuilder.append(getTask(tasks, index)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Displays a user-friendly message when a task is marked as completed
     * @param tasks The list of tasks
     * @param index The index of the completed task
     */
    public String showTaskMarked(List<Task> tasks, int index) {
        return "Nice! I've marked this task as done:\n"
                + getTask(tasks, index);
    }
    /**
     * Displays a user-friendly message when a task is marked as uncompleted
     * @param tasks The list of tasks
     * @param index The index of the uncompleted task
     */
    public String showTaskUnmarked(List<Task> tasks, int index) {
        return "OK, I've marked this task as not done yet:\n"
                + getTask(tasks, index);
    }

    /**
     * Displays a user-friendly message when a task is added
     * @param tasks The list of tasks
     */
    public String showTaskAdded(List<Task> tasks) {
        return "Got it. I've added this task:\n"
                + getTask(tasks, tasks.size() - 1);
    }
    /**
     * Displays a user-friendly message when a task is removed
     * @param task The removed task
     */
    public String showTaskRemoved(Task task) {
        return "Noted. I've removed this task:\n"
                + task.toString();
    }
}
