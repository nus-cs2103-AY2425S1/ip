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
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
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
        assert data != null && !data.trim().isEmpty() : "Data cannot be null or empty";

        String[] parts = data.split(" \\| ");
        assert parts.length >= 3 : "Data must contain at least three parts";

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        switch (type) {
            case "T":
                return createTask(new Todo(description), isDone);
            case "D":
                assert parts.length == 4 : "Deadline task data must contain four parts";
                LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
                return createTask(new Deadline(description, by), isDone);
            case "E":
                assert parts.length == 5 : "Event task data must contain five parts";
                LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
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
        assert description != null : "Description should not be null when converting to string";
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
