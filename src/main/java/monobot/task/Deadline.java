package monobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime dueBy;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    private static final DateTimeFormatter PARSER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String dueByString) throws DateTimeParseException {
        super(description);
        this.dueBy = LocalDateTime.parse(dueByString.trim(), PARSER);
    }

    public LocalDateTime getDueBy() {
        return dueBy;
    }

    public String getDueByString() {
        return dueBy.format(PARSER);
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[D][X] " : "[D][ ] ";
        return status + this.description.trim() + " (by: " + dueBy.format(FORMATTER) + ")";
    }
}
