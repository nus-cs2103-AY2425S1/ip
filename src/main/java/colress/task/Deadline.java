package colress.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate DEADLINE;
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.DEADLINE = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.DEADLINE = deadline;
    }

    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.DEADLINE.isEqual(date);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X][D] %s (%s)", getDescription(),
                    this.DEADLINE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        } else {
            return String.format("[ ][D] %s (%s)", getDescription(),
                    this.DEADLINE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
    }

    @Override
    public String toTextFile() {
        if (getIsDone()) {
            return String.format("[X] | Deadline | %s | %s", getDescription(), this.DEADLINE);
        } else {
            return String.format("[ ] | Deadline | %s | %s", getDescription(), this.DEADLINE);
        }
    }
}
