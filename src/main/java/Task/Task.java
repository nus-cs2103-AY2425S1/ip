package task;

/* My import */
import exception.BlitzException;
import exception.BlitzIOException;

/* System import */
import java.time.LocalDateTime;

public abstract class Task {
    private String desc;
    private boolean isDone;

    /**
     * Constructs a new Task object with specified description and isDone.
     *
     * @param desc String description of this Task object.
     * @param isDone Boolean indicating the task is done or not.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Converts specified String to Task object.
     *
     * @param str String to be converted.
     * @return Task object converted from String (if possible).
     * @throws BlitzException If corrupted file content format or unrecognized type.
     */
    public static Task stringToTask(String str) throws BlitzException {
        String[] params = str.split("::");
        String type = params[0];

        switch (type) {
        case "T":
            if (params.length != 3) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Todo(params[2], "T", Boolean.parseBoolean(params[1]));
        case "D":
            if (params.length != 4) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Deadline(params[2], "D", stringToLocaldatetime(params[3]), Boolean.parseBoolean(params[1]));
        case "E":
            if (params.length != 5) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Event(params[2], "E", stringToLocaldatetime(params[3]), stringToLocaldatetime(params[4]), Boolean.parseBoolean(params[1]));
        default:
            throw new BlitzIOException("Failed to read from database");
        }
    }

    /**
     * Converts specified String to LocalDateTime object.
     *
     * @param str String to be converted.
     * @return LocalDateTime object converted from the specififed String.
     */
    public static LocalDateTime stringToLocaldatetime(String str) {
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
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns the status of this Task object (done or not done).
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets the value of isDone in this object.
     *
     * @param isDone Boolean value to be set.
     */
    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
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
    public abstract String taskToString();

    /**
     * Returns a String representation of this object.
     *
     * @return String representing this object.
     */
    @Override
    public String toString() {
        return this.desc;
    }
}
