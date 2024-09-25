package Mentos.task;

import Mentos.MentosException.MentosException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with a description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event in the format "yyyy-MM-dd HHmm".
     * @param to End time of the event in the format "yyyy-MM-dd HHmm".
     * @throws MentosException if the end time is before the start time.
     */
    public Event(String description, String from, String to) throws MentosException {
        super(description);
        LocalDateTime testFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime testTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates(testFrom, testTo);
        this.from = testFrom;
        this.to = testTo;
    }

    /**
     * Updates the start time of the event.
     *
     * @param from New start time in the format "yyyy-MM-dd HHmm".
     * @throws MentosException if the new start time is after the current end time.
     */
    public void updateFrom(String from) throws MentosException {
        LocalDateTime testFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates(testFrom,this.to);
        this.from = testFrom;
    }

    /**
     * Updates the end time of the event.
     *
     * @param to New end time in the format "yyyy-MM-dd HHmm".
     * @throws MentosException if the new end time is before the current start time.
     */
    public void updateTo(String to) throws MentosException {
        LocalDateTime testTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        validateDates(this.from, testTo);
        this.to = testTo;
    }

    /**
     * Validates that the start time is before the end time.
     *
     * @param from The start time.
     * @param to The end time.
     * @throws MentosException if the end time is before the start time.
     */
    private void validateDates(LocalDateTime from, LocalDateTime to) throws MentosException {
        if (to.isBefore(from)) {
            throw new MentosException("End date must be after start date!");
        }
    }

    /**
     * Returns the string representation of the event, including its description,
     * start time, and end time.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")), this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

}
