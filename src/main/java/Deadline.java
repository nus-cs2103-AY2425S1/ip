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
    public String addTaskToString() {
        return super.addTaskToString() + "\n"
                + " ".repeat(5) + "[D] [ ] " + description + " (by: " + formattedDeadline() + ")" + "\n"
                + "\n"
                + "You can use the command \"list\" to view your list of tasks :D";
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
        }
        return "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    @Override
    public String markDoneToString() {
        return super.markDoneToString() + "\n" + " ".repeat(5) + "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
    }

    @Override
    public String unmarkDoneToString() {
        return super.unmarkDoneToString() + "\n" + " ".repeat(5) + "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
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
