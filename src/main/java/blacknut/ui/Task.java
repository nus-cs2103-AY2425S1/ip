package blacknut.ui;

import blacknut.ui.BlacknutExceptions.IncorrectFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Task class represents a task with a description and a status of completion.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
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
     * Converts a line from the file into a Task object.
     *
     * @param line The line from the file representing the task.
     * @return The Task object represented by the line.
     * @throws IncorrectFormatException If the line format is incorrect.
     */
    public static Task fromFileFormat(String line) throws IncorrectFormatException {
        assert line != null && !line.trim().isEmpty() : "File line cannot be null or empty";

        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IncorrectFormatException("File format incorrect: " + line);
        }
        assert parts.length >= 3 : "File line should contain at least 3 parts";

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length != 4) throw new IncorrectFormatException("File format incorrect: " + line);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
                task = new Deadline(description, by.format(formatter));
                break;
            case "E":
                if (parts.length != 5) throw new IncorrectFormatException("File format incorrect: " + line);
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new IncorrectFormatException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts the Task object to a string format suitable for saving to a file.
     *
     * @return The string representation of the Task for file storage.
     */
    public String toFileFormat() {
        assert description != null && !description.trim().isEmpty() : "Task description should not be null or empty";
        return this.getClass().getSimpleName().substring(0, 1) + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        assert description != null && !description.trim().isEmpty() : "Task description should not be null or empty";
        return "[" + getStatusIcon() + "] " + description;
    }
}
