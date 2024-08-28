package bitbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Adapted from the partial solution in the question
 */
public class Deadline extends Task {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    protected String by;
    protected LocalDateTime localDateTime;
    protected LocalDate localDate;
    protected LocalTime localTime;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline (String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    public Deadline (String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
    }

    public Deadline (String description, LocalTime localTime) {
        super(description);
        this.localTime = localTime;
    }

    /**
     * prints out the final display of the task details to the user.
     * @return prints out the final display of the task details to the user.
     */
    @Override
    public String finalString() {
        if (localDateTime != null) {
            return "[D]" + super.finalString() + " (by: " + localDateTime.format(dateTimeFormatter) + ")";
        } else if (localDate != null) {
            return "[D]" + super.finalString() + " (by: " + localDate.format(dateFormatter) + ")";
        } else if (localTime != null){
            return "[D]" + super.finalString() + " (by: " + localTime.format(timeFormatter) + ")";
        } else {
            return "[D]" + super.finalString() + " (by: " + by + ")";
        }
    }

    /**
     * prints out the final display of the task details to the file to be stored.
     * @return prints out the final display of the task details to the file to be stored.
     */
    @Override
    public String toFileFormat() {

        String byOrDateTimeString;
        if (localDateTime != null) {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + localDateTime.format(dateTimeFormatter) + "|" + "NIL";
        } else if (localDate != null) {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + localDate.format(dateFormatter) + "|" + "NIL";
        } else if (localTime != null){
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + localTime.format(timeFormatter) + "|" + "NIL";
        } else {
            byOrDateTimeString = "D|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + by + "|" + "NIL";
        }

        return byOrDateTimeString;
    }

}
