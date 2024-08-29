package bob.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getBy() {
        return by.toString();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getDate() {
        return by.toLocalDate();
    }

    public static LocalDateTime parseDateTime(String dateTimeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeInput, formatter);
    }

    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
