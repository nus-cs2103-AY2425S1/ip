package miku.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import miku.utility.Priority;
/**
 * Represents an event object.
 */
public class Event extends Task {
    private String dateTime1 = "";
    private String dateTime2 = "";
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Overloads the even constructor with an extra isDone field.
     *
     * @param desc      Descriptions of the event.
     * @param dateTime1 String value of the first date.
     * @param dateTime2 String value of the second date.
     * @param isDone Import is done value
     */
    public Event(String desc, String dateTime1, String dateTime2, boolean isDone, Priority priority) {
        super(desc, isDone, priority);
        this.dateTime1 = dateTime1.trim();
        this.dateTime2 = dateTime2.trim();
        startDateTime = LocalDateTime.parse(this.dateTime1);
        endDateTime = LocalDateTime.parse(this.dateTime2);
    }

    /**
     * Instantiates an even object
     *
     * @param desc      Descriptions of the event.
     * @param dateTime1 String value of the first date.
     * @param dateTime2 String value of the second date.
     */
    public Event(String desc, String dateTime1, String dateTime2) {
        super(desc);
        this.dateTime1 = dateTime1.trim();
        this.dateTime2 = dateTime2.trim();
        startDateTime = LocalDateTime.parse(this.dateTime1);
        endDateTime = LocalDateTime.parse(this.dateTime2);

    }

    public int getStartYear() {
        return startDateTime.getYear();
    }

    public int getStartDayOfYear() {
        return startDateTime.getDayOfYear();
    }



    @Override
    public String stringValue() {
        return "[E]" + super.stringValue()
                + "(from: " + startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                + " to: " + endDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + ")";
    }

    /**
     * Returns the string value to be stored in txt file
     *
     * @return Returns the string value to be stored.
     */
    public String storeValue() {
        return this.stringValue().substring(1, 2)
                + " | " + this.isTaskDone()
                    + " | " + this.getDesc()
                        + " | " + dateTime1 + " | " + dateTime2 + " | " + this.getPriority().toString() + "\n";
    }

}
