package bob.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return "D | " + super.fileFormat() + " | " + this.by.format(INPUT_FORMATTER);
    }

    public void updateDeadline(LocalDateTime newDeadline) {
        this.by = newDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DISPLAY_FORMATTER) + ")";
    }
}
