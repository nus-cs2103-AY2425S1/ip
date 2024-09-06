package Joseph.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends from Task, with additional due timing.
 */
public class Deadline extends Task {
    private final LocalDateTime due;

    /**
     * Initialises a new Deadline object.
     * @param desc The description of the deadline. Should be passed in as a String.
     * @param due The due timing of the deadline. Should be passed in as DD/M/YYYY HHmm.
     */
    public Deadline(String desc, String due) {
        super(desc);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.due = LocalDateTime.parse(due, formatter);
    }

    public LocalDateTime getDue() {
        return this.due;
    }

    @Override
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.getDesc() + " due: " + this.due.format(formatter);
    }
}
