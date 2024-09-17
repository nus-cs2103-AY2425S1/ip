package bangmang.tasks;

import java.time.LocalDateTime;
import bangmang.exception.InvalidTaskFormatException;

/**
 * Represents a task with a description and a status indicating whether it is done.
 * This class also provides methods to mark, unmark, and save tasks.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of whether the task is done.
     *
     * @return true if the task is done, otherwise false.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks the task as done and returns the updated task.
     *
     * @return The updated task marked as done.
     */
    public Task markTask() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as not done and returns the updated task.
     *
     * @return The updated task marked as not done.
     */
    public Task unmarkTask() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string showing the task status and description.
     */
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Reads a saved task from a string and returns the corresponding Task object.
     *
     * @param taskString The string representing the saved task.
     * @return The Task object created from the string.
     * @throws InvalidTaskFormatException if the task format is invalid.
     */
    public static Task readSavedTask(String taskString) throws InvalidTaskFormatException {
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) {
            throw new InvalidTaskFormatException("Alamak, invalid task format: " + taskString);
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                return new Todo(description, isDone);

            case "D":
                if (parts.length != 4) {
                    throw new InvalidTaskFormatException("Alamak, invalid Deadline format: " + taskString);
                }
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                return new Deadline(description, isDone, by);

            case "E":
                if (parts.length != 5) {
                    throw new InvalidTaskFormatException("Alamak, invalid Event format: " + taskString);
                }
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                return new Event(description, isDone, from, to);

            default:
                throw new InvalidTaskFormatException("Alamak, unknown task type: " + taskType);
        }
    }

    /**
     * Writes the task into a string format suitable for saving.
     *
     * @return The string representation of the task for saving.
     * @throws InvalidTaskFormatException if the task cannot be written.
     */
    public String writeSavedTask() throws InvalidTaskFormatException {
        String spacer = " | ";
        String isDone = this.isDone ? "1" : "0";

        if (this instanceof Todo) {
            return "T" + spacer + isDone + spacer + this.description;

        } else if (this instanceof Deadline d) {
            return "D" + spacer + isDone + spacer + d.description + spacer + d.by;

        } else if (this instanceof Event e) {
            return "E" + spacer + isDone + spacer + e.description + spacer + e.from + spacer + e.to;

        } else {
            throw new InvalidTaskFormatException("Alamak, cannot write task: " + this.toString());
        }
    }
}
