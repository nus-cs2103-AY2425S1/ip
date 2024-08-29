package jude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jude.JudeException;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String description, String from, String to) throws JudeException {
        super(description);
        setDatesAndTimes(from, to);
    }

    public Event(String description, String fromDateTime, String toDateTime, boolean isDone) throws JudeException {
        super(description, isDone);
        this.fromDateTime = LocalDateTime.parse(fromDateTime);
        this.toDateTime = LocalDateTime.parse(toDateTime);
    }

    public void setDatesAndTimes(String from, String to) throws JudeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.fromDateTime = LocalDateTime.parse(from, format);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.toDateTime = LocalDateTime.parse(to, dateFormat);
        } catch (DateTimeParseException de) {
            throw new JudeException("wrong date or time format was provided."
                    + " Provide: day/month/year time (e.g. 1/1/2000 1800).");
        }
    }

    @Override
    public String toStringDetails() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toStringDetails(),
                this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s | %s",
                "E", getStatusBinary(), getDescription(), this.fromDateTime, toDateTime);
    }
}
