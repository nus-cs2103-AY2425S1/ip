package pacman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    Deadline(String task, String by) {
        super(task);
        this.by = LocalDate.parse(by);
    }

    public String toFile() {
        return "D/" + super.toFile() + "/" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
