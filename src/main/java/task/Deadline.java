package task;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateStringPrintFormat(this.by) + ")";
    }

    @Override
    public String getDatabaseString() {
        return String.format(
                "D | %d | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                getDateStringStorageFormat(this.by)
        );
    }
}