package atlas.tasks;

import atlas.utils.DateTime;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return String.format("D %s | %s", super.toFileString(),
                this.deadline.format(DateTime.DateTimeFileOutputFormatter));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                this.deadline.format(DateTime.DateTimePrintOutputFormatter));
    }
}
