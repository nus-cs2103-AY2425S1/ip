package FRIDAY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String description, String deadline, int type) {
        super(description, type);
        //accept deadline in YYYY-MM-DD format
        this.deadline = LocalDate.parse(deadline.trim());
    }
    //store deadline in YYYY-MM-DD format
    public String storageDisplay() {
        return "D" + super.storageDisplay() + " | " + deadline;
    }
    @Override
    public String toString() {
        //print it out in MM-DD-YYYY format
        return "[D]" + super.toString() + "(" + deadline.getMonth() + " " + deadline.getDayOfMonth() + " " + deadline.getYear() + ")";
    }
}
