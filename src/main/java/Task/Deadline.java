package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatTime(this.dateTime) + ")";
    }

    public String printTask() {
        return "[D]" + super.toString() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm")) + ")";
    }

    private String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
