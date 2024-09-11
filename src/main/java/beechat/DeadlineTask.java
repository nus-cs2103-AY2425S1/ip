package beechat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DeadlineTask extends Task {
    private LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
