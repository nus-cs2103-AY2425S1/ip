import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String taskItem, String deadlineString, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.deadline = getDeadlineDate(deadlineString);
    }

    private LocalDateTime getDeadlineDate(String deadlineString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadlineString, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MM yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    @Override
    public String toDatabaseFormat() {
        DateTimeFormatter databaseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskItem()  + " | " + this.deadline.format(databaseFormatter);
    }
}
