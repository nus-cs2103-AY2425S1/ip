package Dook.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String fileFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D | " + super.fileFormatted() + " | " + this.by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return "[D]" + super.toString() + " (By: " + this.by.format(formatter) + ")";
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Deadline)) {
            return false;
        } else if (o == this) {
            return true;
        }

        Deadline d = (Deadline) o;

        return d.description.equals(this.description) && d.by.equals(this.by);
    }
}
