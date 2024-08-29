import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String dueDate;
    private LocalDateTime deadline;

    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String description, String dueDate) {
        super(description);
        this.deadline = parseDateTime(dueDate);
    }
    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, inputFormatter);
    }
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    @Override
    public String parseToFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.deadline.format(inputFormatter);
    }
}
