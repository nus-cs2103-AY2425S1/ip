package colress.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task, which contains a String field representing the description, a boolean field reflecting
 * whether the task is marked as done or not, a LocalDate field representing its deadline and two LocalTime fields
 * representing the starting and ending times.
 */
public class Event extends Task {
    private final LocalDate DATE;
    private final LocalTime FROM;
    private final LocalTime TO;

    /**
     * Constructor for the Event class, initialising the description, date, from and to fields with the
     * provided parameters.
     * When a new task is created using this constructor, the task is not done by default.
     */
    public Event(String description, LocalDate date, LocalTime from, LocalTime to) {
        super(description);
        this.DATE = date;
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Constructor for the Event class, initialising all fields with the provided parameters.
     */
    public Event(String description, LocalDate date, LocalTime from, LocalTime to, boolean isDone) {
        super(description, isDone);
        this.DATE = date;
        this.FROM = from;
        this.TO = to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.DATE.isEqual(date);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X][E] %s (%s, %s to %s)", getDescription(),
                    this.DATE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                    this.FROM.format(DateTimeFormatter.ofPattern("HHmm")),
                    this.TO.format(DateTimeFormatter.ofPattern("HHmm")));
        } else {
            return String.format("[ ][E] %s (%s, %s to %s)", getDescription(),
                    this.DATE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                    this.FROM.format(DateTimeFormatter.ofPattern("HHmm")),
                    this.TO.format(DateTimeFormatter.ofPattern("HHmm")));
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toTextFile() {
        if (getIsDone()) {
            return String.format("[X] | Event | %s | %s | %s to %s", getDescription(), this.DATE, this.FROM, this.TO);
        } else {
            return String.format("[ ] | Event | %s | %s | %s to %s", getDescription(), this.DATE, this.FROM, this.TO);
        }
    }
}
