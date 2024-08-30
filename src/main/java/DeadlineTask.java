import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        try {
            LocalDateTime deadlineInFormat = LocalDateTime.parse(deadline);
            this.deadline = deadlineInFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate deadlineInFormat = LocalDate.parse(deadline);
                this.deadline = deadlineInFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e1) {
                this.deadline = deadline;
            }
        }
    }

    @Override
    public String toFileFormat() {
        String divider = " | ";
        String status = this.getStatus() ? "1" : "0";
        return "D" + divider + status + divider + this.getTaskName() + divider + this.deadline;
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[D][X] " + this.getTaskName() + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.getTaskName() + " (by: " + this.deadline + ")";
        }
    }
}
