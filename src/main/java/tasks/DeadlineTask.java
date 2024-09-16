package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private static final String SYMBOL = "D";
    private LocalDate deadlineDate;

    public DeadlineTask(String description, String dueTime) throws DateTimeParseException {
        super(description);
        this.deadlineDate = LocalDate.parse(dueTime);
    }

    public DeadlineTask(String description, String dueTime, String note) throws DateTimeParseException {
        super(description, note);
        this.deadlineDate = LocalDate.parse(dueTime);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTimings() {
        return "(by: " + this.deadlineDate + ")";
    }

    @Override
    public String toString() {
        String taskString = String.format("[%s][%s] %s (by: %s)", this.SYMBOL, super.getStatusIcon(),
                        super.description, this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
