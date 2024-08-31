package rainy.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        try {
            this.compareDate = LocalDate.parse(this.endDate.substring(0, 10));
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String newTime = LocalTime.parse(this.endDate.substring(11, 13) + ":"
                    + this.endDate.substring(13,15)).format(DateTimeFormatter.ofPattern("HH:mm"));
            return "[D] " + super.getName() + " (by " + newDate + " " + newTime + ")";
        } catch (Exception e) {
            this.compareDate = LocalDate.parse(this.endDate.substring(3, 13), DateTimeFormatter.ofPattern("MMM d yyyy"));
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String secondDate = this.endDate.substring(14, 19);
            return "[D] " + super.getName() + " (" + "by " + newDate + " " + secondDate + ")";
        }
    }
}