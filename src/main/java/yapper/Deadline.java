package yapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task, which is a task with a LocalDateTime byDateTime to finish by.
 */
public class Deadline extends Task {
    private LocalDateTime byDateTime;

    /**
     * Creates an instance of Deadline.
     *
     * @param taskName   Name of the task.
     * @param byDateTime Date and Time of ending time of Deadline.
     */
    public Deadline(String taskName, LocalDateTime byDateTime) {
        super(taskName);
        this.byDateTime = byDateTime;
        super.setTaskTag("deadline");
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation of the Deadline object.
     */
    public String toString() {
        String byDateTimeToString = this.byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String doneSymbol = super.getIsDone() ? Task.DONE_SYMBOL : Task.UNDONE_SYMBOL;
        return String.format("[D]%s %s (BY: %s)", doneSymbol, super.getName(), byDateTimeToString);
    }

    /**
     * Returns the string representation of the Deadline object to be recorded into a file.
     * String to be decoded when loading history from the file.
     *
     * @return String representation of the Deadline object.
     */
    public String toFile() {
        String fileDoneSymbol = super.getIsDone() ? Task.FILE_DONE_SYMBOL : Task.FILE_UNDONE_SYMBOL;
        return String.format("D %s--%s--%s", fileDoneSymbol, super.getName(), this.byDateTime);
    }

    public void setByDateTime(LocalDateTime localDateTime) {
        this.byDateTime = localDateTime;
    }
}
