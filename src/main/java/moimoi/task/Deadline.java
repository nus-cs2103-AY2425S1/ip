package moimoi.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(TaskEnum.D, description);
        this.by = by;
    }

    @Override
    public boolean occurringOn(LocalDate date) {
        if (this.by.toLocalDate().isEqual(date)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String stringUI() {
        return super.stringUI() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    @Override
    public String stringStorage() {
        return super.stringStorage() + " | "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
