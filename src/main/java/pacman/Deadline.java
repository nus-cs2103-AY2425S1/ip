package pacman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with deadline. A <code>Deadline</code> object corresponds to
 * a task's name and a deadline date
 */
public class Deadline extends Task {
    private final LocalDate by;

    Deadline(String task, String by) {
        super(task);
        this.by = LocalDate.parse(by);
    }

    /**
     * Return a <code>String</code> that is readable and writeable by <code>Storage</code>
     *
     * @return a <code>String</code> that is readable and writeable by <code>Storage</code>
     */
    public String toFile() {
        return "D/" + super.toFile() + "/" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
