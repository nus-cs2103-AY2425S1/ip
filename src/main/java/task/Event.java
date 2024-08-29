package task;

import utils.exceptions.IllegalValueException;
import utils.formatters.Formatter;
import utils.helpers.HelperFunctions;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Event(String title, String startDate, String endDate) throws IllegalValueException {
        this(title, startDate, endDate, false);
    }

    public Event(String title, String startDate, String endDate, boolean isDone) throws IllegalValueException {
        super(title, isDone);
        this.startDate = HelperFunctions.stringToDateTime(startDate);
        this.endDate = HelperFunctions.stringToDateTime(endDate);
    }

    public boolean ongoingOnDate(LocalDateTime dateTime) {
        return startDate.isBefore(dateTime) && endDate.isAfter(dateTime);
    }

    private String getStartDateString() {
        return String.format(" (from: %s to: %s)", Formatter.dateTimeDisplay(startDate), Formatter.dateTimeDisplay(endDate));
    }

    @Override
    public String storageFormat() {
        return String.format("EVENT | %s | %s | %s | %s", super.getStatus(), super.getTitle(), Formatter.dateTimeStorage(this.startDate), Formatter.dateTimeStorage(this.endDate));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getStartDateString();
    }
}
