package thanos.tasks;

import static thanos.utility.DateTimeUtility.format;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public boolean checkDate(LocalDateTime date) {
        return date.equals(this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), format(this.deadline));
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), format(this.deadline));
    }
}
