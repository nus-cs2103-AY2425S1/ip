import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadline;


    public Deadline(String task, LocalDate deadline, boolean isMarked) {
        super(task, isMarked);
        this.deadline = deadline;
    }

    @Override
    public String printTaskOnList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = deadline.format(formatter);

        if (isMarked) {
            return "[D][X] " + this.task + " (by: " + formattedDate + ")";
        } else {
            return "[D][ ] " + this.task + " (by: " + formattedDate + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isMarked ? "1" : "0") + " | " + this.task + " | " + this.deadline;
    }
}
