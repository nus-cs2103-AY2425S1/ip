import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

// Task with a deadline
public class DeadlineTask extends Task {
    String deadlineString;
    LocalDate deadline;

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadlineString = deadline;
    }

    @Override
    public String taskType() {
        return "Event Task";
    }

    @Override
    public String toString() {
        String formattedDeadline = String.format("(by: %s)", deadline == null ? deadlineString : deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        return "[D]" + super.toString() + " " + formattedDeadline;
    }
}