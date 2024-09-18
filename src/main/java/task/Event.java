package task;

import exceptions.StartAfterEndException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, String start, String end, boolean isCompleted)
            throws StartAfterEndException, DateTimeParseException {
        super(description, isCompleted);

        LocalDateTime startDateTime = parseDateTime(start);
        LocalDateTime endDateTime = parseDateTime(end);

        if (startDateTime.isAfter(endDateTime)) {
            throw new StartAfterEndException();
        }

        this.start = startDateTime;
        this.end = endDateTime;
    }

    public Event(String description, String start, String end) throws StartAfterEndException, DateTimeParseException {
        this(description, start, end, false);
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        String[] args = dateTimeString.split("/at");
        String dateString = args[0].trim();
        String timeString = args[1].trim();

        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);

        return LocalDateTime.of(date, time);
    }

    public static Event of(String data) throws StartAfterEndException {
        String[] args = data.split("\\|");

        boolean isCompleted = Boolean.parseBoolean(args[0].trim());
        String description = args[1].trim();
        String start = args[2].trim();
        String end = args[3].trim();

        return new Event(description, start, end, isCompleted);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'/at 'HH:mm");

        assert start != null : "Start should not be null";
        assert end != null : "End should not be null";

        return super.toData() + "|" + start.format(formatter) + "|" + end.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' at 'HH:mm");
        return String.format("%s (FROM: %s | TO: %s)", super.toString(), start.format(formatter),
                end.format(formatter));
    }
}
