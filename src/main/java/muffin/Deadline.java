package muffin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        String date = by.format(DateTimeFormatter.ofPattern("E, MMM d yyyy"));
        return String.format("[D]%s (by %s)", super.toString(), date);
    }
}
