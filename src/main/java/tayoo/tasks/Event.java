package tayoo.tasks;

import tayoo.Parser;
import tayoo.exception.TayooException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

public class Event extends Task {

    private final String eventStartStr;
    private final String eventEndStr;
    private final TemporalAccessor eventStart;
    private final TemporalAccessor eventEnd;

    /**
     * Constructs a new 'Event' object which can be added to Tayoo's list. Event is defined as a task with a start and
     * end date/time.
     *
     * @param title title of the event
     * @param start start date/time of the event
     * @param end end date/time of the event
     * @param completed whether the event has been completed or not
     */
    public Event(String title, String start, String end, boolean completed) {
        super(title, completed);
        this.eventStart = Parser.parseDateTime(start);
        this.eventEnd = Parser.parseDateTime(end);
        this.eventStartStr = start;
        this.eventEndStr = end;
    }

    /**
     * Constructs a new 'Event' object which can be added to Tayoo's list. Event is defined as a task with a start and
     * end date/time. Assumes the event has not been completed.
     *
     * @param title title of the event
     * @param start start date/time of the event
     * @param end end date/time of the event
     */
    public Event(String title, String start, String end) {
        super(title);
        this.eventStart = Parser.parseDateTime(start);
        this.eventEnd = Parser.parseDateTime(end);
        this.eventStartStr = start;
        this.eventEndStr = end;
    }

    @Override
    public String toString() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startString, endString;

        if (eventStart != null) {
            if (eventStart instanceof LocalDateTime) {
                startString = dateTimeFormatter.format((LocalDateTime) eventStart);
            } else if (eventStart instanceof LocalTime) {
                startString = timeFormatter.format((LocalTime) eventStart);
            } else {
                startString = eventStart.toString();
            }
        } else {
            startString = Objects.requireNonNullElse(eventStartStr, "");
        }

        if (eventEnd != null) {
            if (eventEnd instanceof LocalDateTime) {
                endString = dateTimeFormatter.format((LocalDateTime) eventEnd);
            } else if (eventEnd instanceof LocalTime) {
                endString = timeFormatter.format((LocalTime) eventEnd);
            } else {
                endString = eventEnd.toString();
            }
        } else {
            endString = Objects.requireNonNullElse(eventEndStr, "");
        }

        return "[E]" + super.toString() + " (from: " + startString + " to: " + endString + ")";
    }

    /**
     * Returns the Event in a form that can be stored as data in a .txt file
     * Tasks will be stored in the format "Event" | [true OR false] | [Event title] | [start] | [end]. The value true
     * will be stored if the Event is completed, and false otherwise.
     *
     * @return string representation of Event in command form
     */
    public String toTxt() throws TayooException {

        String startTxtString, endTxtString;

        if (eventStart != null) {
            startTxtString = eventStart.toString();
        } else if (eventStartStr != null){
            startTxtString = eventStartStr;
        } else {
            throw new TayooException("No event start string");
        }

        if (eventEnd != null) {
            endTxtString = eventEnd.toString();
        } else if (eventEndStr != null){
            endTxtString = eventEndStr;
        } else {
            throw new TayooException("No event end string");
        }


        return "Event | " + isCompleted() + " | " + this.getTitle() + " | " + startTxtString + " | " + endTxtString;
    }
}
