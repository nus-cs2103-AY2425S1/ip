package colress.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task, which contains a String field representing the description, a boolean field reflecting
 * whether the task is marked as done or not and a LocalDate field representing its deadline.
 */
public class Deadline extends Task {
    private final LocalDate DEADLINE;

    /**
     * Constructor for the Event class, initialising the description, date, from and to fields with the
     * provided parameters.
     * When a new task is created using this constructor, the task is not done by default.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.DEADLINE = deadline;
    }

    /**
     * Constructor for the Event class, initialising all fields with the provided parameters.
     */
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
