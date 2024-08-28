package justbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    public LocalDateTime getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, hhmma");
        String formattedBy = this.by.format(formatter).replace("AM", "hrs").replace("PM", "hrs");

        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

}
