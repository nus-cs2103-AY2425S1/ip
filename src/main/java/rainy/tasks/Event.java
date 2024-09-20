package rainy.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. This class is a sub-class of the <code>Task</code> class.
 */
public class Event extends Task {
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    private static final int FIRST_TIME_HOUR_START = 0;
    private static final int FIRST_TIME_HOUR_END = 2;
    private static final int FIRST_TIME_MIN_START = 2;
    private static final int FIRST_TIME_MIN_END = 4;
    private static final int FIRST_TIME_START = 2;
    private static final int SECOND_TIME_START = 10;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Represents the task in a readable format. If the task is read from an existing file, it is directly read into
     * name of the event. Else, this method does additional formatting to represent the date in a standard format.
     * {@code
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
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            String firstTime = LocalTime.parse(this.time.substring(FIRST_TIME_HOUR_START, FIRST_TIME_HOUR_END)
                            + ":" + this.time.substring(FIRST_TIME_MIN_START, FIRST_TIME_MIN_END))
                                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            String secondTime = LocalTime.parse(this.time.substring(8, 10) + ":"
                    + this.time.substring(10, 12)).format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            return "[E] " + super.getName() + "(" + newDate + " from "
                    + firstTime + " to " + secondTime + ")";
        } catch (Exception e) {
            try {
                this.compareDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern(DATE_FORMAT));
            } catch (Exception d) {
                this.compareDate = LocalDate.parse(this.date);
            }
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            return this.time.contains(":") ? "[E] " + super.getName() + "(" + newDate + " from " + this.time + ")"
                    : "[E] " + super.getName() + "(" + newDate + " from "
                        + this.time.substring(FIRST_TIME_HOUR_START, FIRST_TIME_HOUR_END) + ":"
                            + this.time.substring(FIRST_TIME_START, SECOND_TIME_START) + ":"
                                + this.time.substring(SECOND_TIME_START) + ")";
        }
    }
}
