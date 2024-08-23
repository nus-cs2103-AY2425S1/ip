package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends IndividualTask{

    private String returnBy;
    private LocalDateTime formatReturnBy;
    public Deadline(String taskDescription, String returnBy) {
        super(taskDescription);
        this.returnBy = returnBy;
        this.formatReturnBy = parseDateTime(returnBy);
    }
    public LocalDateTime parseDateTime(String date) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(date, formatDate);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong format for data: " + date);
            return null;
        }
    }

    public String formatDateTime() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return this.formatReturnBy.format(outputFormatter);
    }
    @Override
    public String saveToFileFormat() {
        return "D | " +
                this.getSaveIcon() +
                " | " +
                this.getTaskDescription() +
                " | " + this.returnBy;
    }
    @Override
    public String toString() {
        return "[D]" +
                super.toString() +
                " (by: " + this.formatDateTime() + ")";
    }
}
