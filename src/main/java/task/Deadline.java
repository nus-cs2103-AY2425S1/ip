package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate end;
    public Deadline(String description, String end) {
        super(description);
        this.end = LocalDate.parse(end);
    }
    @Override
    public String toString() {
        String ending = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + ending + ")";
    }

    @Override
    public String toSave() {
        return "D" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.end;
    }
}
