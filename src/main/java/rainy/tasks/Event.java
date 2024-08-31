package rainy.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String date;
    private String time;

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