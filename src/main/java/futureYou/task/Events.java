package futureYou.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Task {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Events(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDateTime = startDate;
        this.endDateTime = endDate;
    }

    public Events(String name, Boolean taskStatus, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, taskStatus);
        this.startDateTime = startDate;
        this.endDateTime = endDate;
    }

    public String getstartDate() {
        return this.startDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm"));
    }

    public String getendDate() {
        return this.endDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm"));
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.getstartDate() + "|" + this.getendDate();
    }

    @Override
    public String print() {
        return super.print() + " (from: " + this.getstartDate() + " to: " + this.getendDate() + ")";
    }
}