package Task;

import Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Deadline extends Task {

    LocalDateTime by;
    String savedBy;
    public Deadline(String description, String by) {
        super(description);
        this.savedBy = by;
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.savedBy = by;
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
    }
    @Override
    public String toString() {
        //return "[D]" + super.toString() + " (by: " + by + ")";
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMMM dd yyyy, ha", Locale.ENGLISH)) + ")";
    }

    @Override
    public String save() {
        return "D | " + super.save() + " | " + savedBy;
    }
}
