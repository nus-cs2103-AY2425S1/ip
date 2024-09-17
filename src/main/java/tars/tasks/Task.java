package tars.tasks;

import java.time.LocalDate;
import java.util.Date;

/**
 * Represents a task with a name and a completion status.
 *
 * <p>The {@code Task} class provides basic functionalities for a task, such as
 * marking it as done or undone, retrieving its status, and converting it to a string
 * representation. This class can be extended by other classes to represent specific
 * types of tasks (e.g., ToDo, Deadline, Event).</p>
 */
public abstract class Task implements Comparable<Task> {

    private final String taskName;
    private boolean isComplete;

    /**
     * Constructs a {@code Task} with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        taskName = name;

    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isComplete = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markUndone() {
        isComplete = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return "1" if the task is complete, otherwise "0".
     */
    public String getStatus() {
        return isComplete ? "1" : "0";
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTask() {
        return taskName;
    }

    /**
     * Prepares the task information for saving to a file.
     *
     * @return A {@link String} representing the serialized task information. This method
     *         is intended to be overridden by subclasses to provide specific task formats.
     */
    public abstract String saveTask();

    /**
     * Returns the date of a task to be used to sort tasks.
     *
     * @return A {@link Date} representing the date the task needs to be done by
     */
    public abstract LocalDate getDate();

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A formatted string representing the task's status and name.
     */
    @Override
    public String toString() {
        String status;

        if (isComplete) {
            status = "[X]";

        } else {
            status = "[ ]";

        }

        return String.format("%s %s", status, taskName);
    }

    @Override
    public int compareTo(Task t) {

        LocalDate thisDate = this.getDate();
        LocalDate compareDate = t.getDate();

        if (thisDate == null) {
            if (compareDate == null) {
                return 0;
            }
            return 1;

        } else {
            if (compareDate != null) {
                return thisDate.compareTo(compareDate);
            }
            return -1;
        }
    }
}
