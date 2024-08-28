package astra.task;

import astra.AstraException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate by;

    public Deadline(String name, String by) throws AstraException {
        super(name);
        this.by = LocalDate.parse(by, inputFormatter);
    }

    public Deadline(boolean done, String name, String by) {
        super(done, name);
        this.by = LocalDate.parse(by, inputFormatter);
    }

    @Override
    public String toText() {
        return "D | " + super.toText() + " | " + by.format(inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by.format(outputFormatter));
    }
}
