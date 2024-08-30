package task;

import components.Parser;
import exceptions.LightException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws LightException {
        super(description);
        this.by = Parser.dateTimeParser(by, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}