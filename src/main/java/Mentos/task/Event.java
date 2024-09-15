package Mentos.task;

import Mentos.MentosException.MentosException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws MentosException {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates();
    }

    public void updateFrom(String from) throws MentosException {
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates();
    }

    public void updateTo(String to) throws MentosException {
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates();
    }

    private void validateDates() throws MentosException {
        if (this.to.isBefore(this.from)) {
            throw new MentosException("End date must be after start date!");
        }
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")), this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

}
