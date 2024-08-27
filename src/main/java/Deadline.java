// import libraries necessary for bestie to understand the dates
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    private String formattedDeadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        LocalDate deadline = LocalDate.parse(by);
        formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}