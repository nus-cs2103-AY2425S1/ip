import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime byDateTime;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDateTime = byDate.atStartOfDay();
    }

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
        }
        return "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    @Override
    public String markDone() {
        return super.markDone() + "\n" + " ".repeat(5) + "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
    }

    @Override
    public String unmarkDone() {
        return super.unmarkDone() + "\n" + " ".repeat(5) + "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n" + " ".repeat(5) + "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
        }
        return super.deleteTask() + "\n" + " ".repeat(5) + "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    public String formattedDeadline() {
        if (byDateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return byDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
        }
        return byDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy 'at' HHmm"));
    }

}
