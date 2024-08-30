package luke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER
            = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
    protected static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER
            = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    protected String deadline;

    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        if (deadline.charAt(0) == '0') {
            deadline = deadline.substring(1, deadline.length() - 1);
        }
        try {
            LocalDateTime parsedDateInput = LocalDateTime.parse(deadline, DATE_TIME_INPUT_FORMATTER);
            this.deadline = parsedDateInput.format(DATE_TIME_OUTPUT_FORMATTER);
        } catch (java.time.format.DateTimeParseException e) {
            // assuming the input is already formatted in the output format
            this.deadline = deadline;
        }
    }

    @Override
    public String taskDescription() {
        return "[D]" + showMark() + " " + this.name
                + " (by: " + deadline + ")";
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | deadline | "
                + name + " by " + deadline + "\n";
    }
}
