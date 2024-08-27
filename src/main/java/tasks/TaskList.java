package tasks;

import java.util.ArrayList;

import dateandtime.ReginaDateAndTime;

/**
 * The tasks.TaskList class represents a list of tasks.
 * It extends ArrayList<Task.Task> to provide a custom implementation
 * that holds Task.Task objects and includes functionality specific
 * to managing tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs a TaskList instance.
     * This constructor initializes an empty TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Retrieves a TaskList containing tasks that occur at the specified date and time.
     *
     * <p>This method iterates through the current task list and checks whether each task
     * occurs on the provided date and time. If a task matches, it is added to the
     * returned TaskList.
     *
     * @param dateAndTime The date and time to check for occurring tasks.
     *                    Should be an instance of {@link ReginaDateAndTime}.
     * @return A new TaskList containing the tasks that occur at the specified date and time.
     */
    public TaskList findTasksOccurringOn(ReginaDateAndTime dateAndTime) {
        TaskList list = new TaskList();
        for (Task task : this) {
            if (task.isOccurringOn(dateAndTime)) {
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Converts the current task list into a string format suitable for saving.
     *
     * <p>This method calls the task's toSavedFormatting() method to generate a string
     * representation for each task, concatenating them with newline characters.
     *
     * @return A string representation of all tasks in the list formatted for saving.
     */
    public String toSavedFormatting() {
        StringBuilder tasksRepresentation = new StringBuilder();

        for (Task task : this) {
            tasksRepresentation
                    .append(task.toSavedFormatting()) // Calls the toString of Task.
                    .append("\n"); // New line for each task.
        }

        return tasksRepresentation.toString(); // Returns the final string representation.
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword.
     *
     * <p>This method iterates through the current task list and checks whether each task's
     * string representation contains the given keyword. If a match is found, the task is
     * added to a new TaskList, which is returned at the end of the search.
     *
     * @param keyword The keyword to search for within task descriptions.
     *                This may be part of the task description and is case-sensitive.
     * @return A new {@link TaskList} containing all tasks that match the keyword,
     *         or an empty TaskList if no matches are found.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        TaskList list = new TaskList();
        for (Task task : this) {
            if (task.toString().contains(keyword)) {
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the tasks in the current list.
     *
     * <p>If the list is empty, a message indicating no tasks are present is returned.
     * Otherwise, each task is enumerated and represented as a string for easy display to the user.
     *
     * @return A formatted string listing all tasks in the TaskList.
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "don't waste your time, nothing happening then.\n";
        }
        StringBuilder taskList = new StringBuilder();
        int count = 1;
        for (Task task : this) {
            taskList.append(count).append(
                    String.format(". %s\n", task.toString()));
            count++;
        }
        return taskList.toString();
    }
}
