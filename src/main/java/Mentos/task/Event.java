package Mentos.task;

import Mentos.MentosException.MentosException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws MentosException {
        super(description);
        LocalDateTime testFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime testTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates(testFrom, testTo);
        this.from = testFrom;
        this.to = testTo;
    }

    public void updateFrom(String from) throws MentosException {
        LocalDateTime testFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates(testFrom,this.to);
        this.from = testFrom;
    }

    public void updateTo(String to) throws MentosException {
        LocalDateTime testTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates(this.from, testTo);
        this.to = testTo;
    }

    private void validateDates(LocalDateTime from, LocalDateTime to) throws MentosException {
        if (to.isBefore(from)) {
            throw new MentosException("End date must be after start date!");
        }
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")), this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

}
