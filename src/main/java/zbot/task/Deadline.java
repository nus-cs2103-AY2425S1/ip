package zbot.task;

import java.time.LocalDateTime;
import zbot.Parser;

public class Deadline extends Task {
    protected LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                Parser.formatDateTimeToOutput(dueDate));
    }
}
