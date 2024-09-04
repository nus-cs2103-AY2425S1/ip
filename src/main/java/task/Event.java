package task;

import utils.exceptions.IllegalValueException;
import utils.formatters.Formatter;
import utils.helpers.HelperFunctions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public Event(String title, String startDateTime, String endDateTime) throws IllegalValueException {
        this(title, startDateTime, endDateTime, false);
    }

    public Event(String title, String startDateTime, String endDateTime, boolean isDone) throws IllegalValueException {
        super(title, isDone);
        this.startDateTime = HelperFunctions.stringToDateTime(startDateTime);
        this.endDateTime = HelperFunctions.stringToDateTime(endDateTime);
    }

    public boolean ongoingOnDate(LocalDateTime dateTime) {
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();
        LocalDate date = dateTime.toLocalDate();
        return (startDate.isBefore(date) && endDate.isAfter(date)) || startDate.isEqual(date) || endDate.isEqual(date);
    }

    private String getStartDateString() {
        return String.format(" (from: %s to: %s)", Formatter.dateTimeDisplay(startDateTime), Formatter.dateTimeDisplay(endDateTime));
    }

    @Override
    public String storageFormat() {
        return String.format("EVENT | %s | %s | %s | %s", super.getStatus(), super.getTitle(), Formatter.dateTimeStorage(this.startDateTime), Formatter.dateTimeStorage(this.endDateTime));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getStartDateString();
    }
}
