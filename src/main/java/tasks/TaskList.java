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
        for (int i = 0; i < this.toArray().length; i++) {
            Task currTask = (Task)this.toArray()[i];
            if (currTask.isOccurringOn(dateAndTime)) {
                list.add(currTask);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder tasksRepresentation = new StringBuilder();

        for (Task task : this) {
            tasksRepresentation
                    .append(task.toSavedFormatting()) // Calls the toString of Task.
                    .append("\n"); // New line for each task.
        }

        return tasksRepresentation.toString();  // Returns the final string representation.
    }
}
