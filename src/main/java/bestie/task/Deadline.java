package bestie.task;// import libraries necessary for bestie to understand the dates
import bestie.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime deadline;
    private String formattedDeadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.deadline = LocalDateTime.parse(by, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        this.formattedDeadline = this.deadline.format(outputFormat);
    }


    @Override
    public String toSaveFormat() {
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // store format: " D | 0 | read book | by Sunday
        return "D | " + storeCompleted + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedDeadline + ")";
    }
}