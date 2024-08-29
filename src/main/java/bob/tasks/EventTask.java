package bob.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String description, LocalDateTime from, String to) {
        super(description);
        this.from = from;
        this.to = parseToDateTime(to);
    }

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static LocalDateTime parseDateTime(String dateTimeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeInput, formatter);
    }

    protected LocalDateTime parseToDateTime(String toInput) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            // Try parsing as full date and time
            return LocalDateTime.parse(toInput, fullFormatter);
        } catch (DateTimeParseException e) {
            // If fails, parse as just time and use 'from' date
            LocalTime time = LocalTime.parse(toInput, timeFormatter);
            return LocalDateTime.of(from.toLocalDate(), time);
        }
    }

    @Override
    public LocalDate getDate() {
        return from.toLocalDate();
    }

    @Override
    public String getFrom() {
        return from.toString();
    }

    @Override
    public String getTo() {
        return to.toString();
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"));
        String toFormatted = to.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"));
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }
}