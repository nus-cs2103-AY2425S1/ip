package bitbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Adapted from the partial solution in the question
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime localDateTime;
    protected LocalDate localDate;
    protected LocalTime localTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * This is a Deadline constructor that takes in the description of the task
     * and takes in the time to end the task by
     *
     * @param description the description of the task
     * @param by the time to end the task by in a String format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This is a Deadline constructor that takes in the description of the task
     * and takes in the time to end the task by in dd/MM/yyyy HH:mm format
     *
     * @param description the description of the event
     * @param localDateTime the time to end the task in dd/MM/yyyy HH:mm format
     */
    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    /**
     * This is a Deadline constructor that takes in the description of the task
     * and takes in the time to end the task by in dd/MM/yyyy format
     *
     * @param description the description of the event
     * @param localDate the time to end the task in dd/MM/yyyy format
     */
    public Deadline(String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
    }

    /**
     * This is a Deadline constructor that takes in the description of the task
     * and takes in the time to end the task by in HH:mm format
     *
     * @param description the description of the event
     * @param localTime the time to end the task in HH:mm format
     */
    public Deadline(String description, LocalTime localTime) {
        super(description);
        this.localTime = localTime;
    }

    /**
     * Prints out the final display of the task details to the user.
     * @return Prints out the final display of the task details to the user.
     */
    @Override
    public String finalString() {
        if (localDateTime != null) {
            return "[D]" + super.finalString() + " (by: " + localDateTime.format(dateTimeFormatter) + ")";
        } else if (localDate != null) {
            return "[D]" + super.finalString() + " (by: " + localDate.format(dateFormatter) + ")";
        } else if (localTime != null) {
            return "[D]" + super.finalString() + " (by: " + localTime.format(timeFormatter) + ")";
        } else {
            return "[D]" + super.finalString() + " (by: " + by + ")";
        }
    }

    /**
     * Prints out the final display of the task details to the file to be stored.
     * @return Prints out the final display of the task details to the file to be stored.
     */
    @Override
    public String toFileFormat() {

        String byOrDateTimeString;
        if (localDateTime != null) {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + localDateTime.format(dateTimeFormatter) + "|" + "NIL" + "|" + tag;
        } else if (localDate != null) {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + localDate.format(dateFormatter) + "|" + "NIL" + "|" + tag;
        } else if (localTime != null) {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + localTime.format(timeFormatter) + "|" + "NIL" + "|" + tag;
        } else {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + by + "|" + "NIL" + "|" + tag;
        }
        return byOrDateTimeString;
    }

}
