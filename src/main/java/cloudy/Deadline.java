package cloudy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate deadline;


    public Deadline(String description, LocalDate deadline, boolean isMarked) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    @Override
    public String printTaskOnList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = deadline.format(formatter);

        if (isMarked) {
            return "[D][X] " + this.description + " (by: " + formattedDate + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + formattedDate + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isMarked ? "1" : "0") + " | " + this.description + " | " + this.deadline;
    }
}
