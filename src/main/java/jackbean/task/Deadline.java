package jackbean.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    // make it MMM dd yyyy format
    public String getDateString(LocalDate date) {
        return date.getMonth().toString().substring(0, 1) + date.getMonth().toString().substring(1, 3).toLowerCase()
                + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + getDateString(this.by) + ")";
    }
}
