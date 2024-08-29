package task;

import java.time.LocalDate;
import java.time.Month;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getLocalDate(by) + ")";
    }

    private String getLocalDate(LocalDate date) {
        return Month.of(date.getMonthValue()).toString() + " " + date.getDayOfMonth() + " " + date.getYear();
    }
}
