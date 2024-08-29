package donna.task;

import donna.DonnaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime by;
    private final String description;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Deadline(String description, String by) throws DonnaException {
        super(description);
        if (by.trim().isEmpty()) {
            throw DonnaException.emptyDeadline();
        }
        try {
            this.by = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DonnaException("Invalid date and time format! Please use dd/MM/yyyy HHmm (24hr format)");
        }
//        this.by = by;
        this.description = description;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.description + " | " + this.by.format(inputFormatter);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() // type, status & desc
                + "(by: " + this.by.format(outputFormatter) + ")";
    }
}