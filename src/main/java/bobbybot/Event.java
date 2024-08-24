package bobbybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDate start;
    private final LocalDate end;

    private final DateTimeFormatter EVENT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, String start, String end) throws DukeException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid start and/or end date in the format yyyy-mm-dd.");
        }

        if (this.start.isAfter(this.end)) {
            throw new DukeException("Please enter an end date that is after the start date.");
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]%s (from: %s to: %s)",
                getTaskType(),
                super.toString(),
                start.format(EVENT_DATE_FORMAT),
                end.format(EVENT_DATE_FORMAT)
        );
    }

    @Override
    public String getFileString() {
        return getTaskType() + " | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + start + " | " + end;
    }
}