package tasks;

import dateAndTime.ReginaDateAndTime;

import java.util.ArrayList;

/**
 * The tasks.TaskList class represents a list of tasks.
 * It extends ArrayList<Task.Task> to provide a custom implementation
 * that holds Task.Task objects and includes functionality specific
 * to managing tasks.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList tasksOccurringOn(ReginaDateAndTime dateAndTime) {
        TaskList list = new TaskList();
        for (Task task : this) {
            if (task.isOccurringOn(dateAndTime)) {
                list.add(task);
            }
        }
        return list;
    }

    public String toSavedFormatting() {
        StringBuilder tasksRepresentation = new StringBuilder();

        for (Task task : this) {
            tasksRepresentation
                    .append(task.toSavedFormatting()) // Calls the toString of Task.
                    .append("\n"); // New line for each task.
        }

        return tasksRepresentation.toString();  // Returns the final string representation.
    }

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
