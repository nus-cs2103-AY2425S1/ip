package barcus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
//    protected String by;
    protected LocalDateTime by;
    private final static DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final static DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, fromFormatter);
    }

    public Deadline(String description, boolean isDone, String by) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, fromFormatter);
    }

    @Override
    public String convertToSavedString() {
        return "D | " + super.convertToSavedString() + " | " + this.by.format(fromFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(toFormatter) + ")";
    }
}
