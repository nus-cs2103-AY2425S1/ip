package tasks;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }

    @Override
    public String saveFormat() {
        return "D" + super.saveFormat() + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
