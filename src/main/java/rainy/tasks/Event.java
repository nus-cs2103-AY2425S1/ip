package rainy.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import rainy.database.*;
import rainy.rainyexceptions.*;

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
            return "[E] " + super.getName() + " (" + this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " from " + LocalTime.parse(this.time.substring(0, 2) + ":" + this.time.substring(2, 4)).format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                    + LocalTime.parse(this.time.substring(8, 10) + ":" + this.time.substring(10, 12)).format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } catch (Exception e) {
            this.compareDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E] " + super.getName() + "(" + this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " from " + this.time + ")";
        }

    }
}