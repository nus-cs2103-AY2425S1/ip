package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {

    private final LocalDateTime date;

    public DeadLine(String _description, LocalDateTime date) {
        super(_description);
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getDate() + ")";
    }

    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + "\n";
    }
}
