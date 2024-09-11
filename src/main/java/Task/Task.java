package task;

import java.time.LocalDateTime;

import exception.BlitzException;
import exception.BlitzIoException;

/**
 * Represents an abstract task with a description and completion status in the Blitz application.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object with specified description and isDone.
     *
     * @param description String description of this Task object.
     * @param isDone Boolean indicating the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts specified String to Task object.
     *
     * @param str String to be converted.
     * @return Task object converted from String (if possible).
     * @throws BlitzException If corrupted file content format or unrecognized type.
     */
    public static Task convertStringToTask(String str) throws BlitzException {
        String[] taskDetails = str.split("::");
        String type = taskDetails[0];

        switch (type) {
        case "T":
            if (taskDetails.length != 3) {
                throw new BlitzIoException("Failed to read from database");
            }

            String todoDescription = taskDetails[2];
            boolean todoStatus = Boolean.parseBoolean(taskDetails[1]);

            return new Todo(todoDescription, type, todoStatus);
        case "D":
            if (taskDetails.length != 4) {
                throw new BlitzIoException("Failed to read from database");
            }

            String deadlineDescription = taskDetails[2];
            LocalDateTime dateTime = convertStringToLocalDateTime(taskDetails[3]);
            boolean deadlineStatus = Boolean.parseBoolean(taskDetails[1]);

            return new Deadline(deadlineDescription, type, dateTime, deadlineStatus);
        case "E":
            if (taskDetails.length != 5) {
                throw new BlitzIoException("Failed to read from database");
            }

            String eventDescription = taskDetails[2];
            LocalDateTime startDateTime = convertStringToLocalDateTime(taskDetails[3]);
            LocalDateTime endDateTime = convertStringToLocalDateTime(taskDetails[4]);
            boolean eventStauts = Boolean.parseBoolean(taskDetails[1]);

            return new Event(eventDescription, type, startDateTime, endDateTime, eventStauts);
        default:
            throw new BlitzIoException("Failed to read from database");
        }
    }

    /**
     * Converts specified String to LocalDateTime object.
     *
     * @param str String to be converted.
     * @return LocalDateTime object converted from the specififed String.
     */
    public static LocalDateTime convertStringToLocalDateTime(String str) {
        int year = Integer.parseInt(str.substring(0, 4));
        int month = Integer.parseInt(str.substring(5, 7));
        int day = Integer.parseInt(str.substring(8, 10));
        int hour = Integer.parseInt(str.substring(11, 13));
        int min = Integer.parseInt(str.substring(13, 15));

        return LocalDateTime.of(year, month, day, hour, min);
    }

    /**
     * Returns the description of this Task object.
     *
     * @return String representation of the description of this Task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of this Task object (done or not done).
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the value of isDone in this object.
     *
     * @param isDone Boolean value to be set.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the type of this object.
     *
     * @return String representation of the type of this object.
     */
    public abstract String getType();

    /**
     * Converts this object to String representation (different format with toString()).
     *
     * @return String representation of this object.
     */
    public abstract String convertTaskToString();


    /**
     * Compares two Task objects and determines if they are equal without considering status.
     *
     * @param task Task to be compared.
     * @return True if both objects are of same reference or all attributes (except status)
     *     in both objects are the same, false otherwise.
     */
    public abstract boolean isEqualWithoutStatus(Task task);

    /**
     * Returns a String representation of this object.
     *
     * @return String representing this object.
     */
    @Override
    public String toString() {
        return getDescription();
    }
}
