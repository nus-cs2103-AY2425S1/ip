package rainy.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. This class is a sub-class of the <code>Task</code> class.
 */
public class Event extends Task {
    private String date;
    private String time;

    /**
     * Constructs a new <code>Event</code> object.
     * @param name  Represents the name of the task.
     * @param date  Represents the date of the task.
     * @param time  Represents the timeframe of the task.
     */
    public Event(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    /**
     * Represents the task in a readable format. If the task is read from an existing file, it is directly read into
     * name of the event. Else, this method does additional formatting to represent the date in a standard format.
     *{@code
     * Event event = new Event("project meeting", "02-12/2024", "1800 to 2000");
     * System.out.println(event);
     *
     * // Expected output:
     * // [E] project meeting  (Aug 19 2024 from 18:00 to 20:00)
     * }
     * @return  Returns a string representing the <code>Event</code> object.
     */
    @Override
    public String toString() {
        try {
            this.compareDate = LocalDate.parse(this.date);
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String firstTime = LocalTime.parse(this.time.substring(0, 2) + ":"
                    + this.time.substring(2, 4)).format(DateTimeFormatter.ofPattern("HH:mm"));
            String secondTime = LocalTime.parse(this.time.substring(8, 10) + ":"
                    + this.time.substring(10, 12)).format(DateTimeFormatter.ofPattern("HH:mm"));
            return "[E] " + super.getName() + " (" + newDate + " from "
                    + firstTime + " to " + secondTime + ")";
        } catch (Exception e) {
            this.compareDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("MMM d yyyy"));
            String newDate =  this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E] " + super.getName() + "(" + newDate + " from " + this.time + ")";
        }

    }
}