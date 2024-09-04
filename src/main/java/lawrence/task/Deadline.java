package lawrence.task;

import lawrence.utils.DateParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isComplete, LocalDateTime by) {
        super(description, isComplete);
        this.by = by;
    }

    public String toSaveFormat() {
        return String.format("D | %s | %s",
                super.toSaveFormat(),
                DateParser.toOutputString(by));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                DateParser.toOutputString(by));
    }
}
