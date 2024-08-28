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
            return "[E] " + super.getName() + "(" + LocalDate.parse(this.date)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " from " + LocalTime.parse(this.time.substring(0, 2) + ":" + this.time.substring(2, 4)).format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                    + LocalTime.parse(this.time.substring(8, 10) + ":" + this.time.substring(10, 12)).format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } catch (Exception e) {
            return "[E] " + super.getName() + "(" + this.date + " from " + this.time + ")";
        }

    }
}