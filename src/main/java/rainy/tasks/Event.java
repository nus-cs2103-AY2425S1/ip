package rainy.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import rainy.database.*;
import rainy.rainyexceptions.*;

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
            return "[E] " + super.getName() + " (" + this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " from " + LocalTime.parse(this.time.substring(0, 2) + ":" + this.time.substring(2, 4)).format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                    + LocalTime.parse(this.time.substring(8, 10) + ":" + this.time.substring(10, 12)).format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } catch (Exception e) {
            this.compareDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E] " + super.getName() + "(" + this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " from " + this.time + ")";
        }

    }
}