package arts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import arts.ArtsException;

/**
 * Represents an abstract task with a description and completion status.
 * Provides basic functionalities for marking tasks as done or not done,
 * and converting tasks to and from file storage formats.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done or not.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Abstract method to convert the task to a string format suitable for file storage.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();

    /**
     * Creates a Task object from a string formatted for file storage.
     * This method parses the string, determines the task type, and initializes
     * the appropriate Task subclass.
     *
     * @param data The string data representing the task in file format.
     * @return A Task object corresponding to the given data.
     * @throws ArtsException If the task type is unknown or data is invalid.
     */
    public static Task fromFileFormat(String data) throws ArtsException {
        String[] parts = data.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return createTask(new Todo(description), isDone);
        case "D":
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(parts[3], deadlineFormatter);
            return createTask(new Deadline(description, by), isDone);
        case "E":
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(parts[3], eventFormatter);
            LocalDateTime to = LocalDateTime.parse(parts[4], eventFormatter);
            return createTask(new Event(description, from, to), isDone);
        default:
            throw new ArtsException("Unknown task type.");
        }
    }

    private static Task createTask(Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
