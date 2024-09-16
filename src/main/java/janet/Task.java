package janet;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Task
 */
public class Task implements Comparable<Task> {
    private final String description;
    private final String symbol;
    private boolean isMarked;

    /**
     * @param description The description of the task.
     * @param symbol The symbol of the task.
     */
    public Task(String description, String symbol) {
        this.description = description;
        this.isMarked = false;
        this.symbol = symbol;
    }

    /**
     * Returns the scheduledDateAndTime in LocalDateTime.
     *
     * @return null.
     */
    public LocalDateTime getScheduledDateAndTime() {
        return null;
    }

    /**
     * Returns the date portion of scheduledDateAndTime, in LocalDate.
     *
     * @return null.
     */
    public LocalDate getScheduledDate() {
        return null;
    }

    /**
     * @return task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return task's symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * @return the value of this.done, indicating whether the task is done (marked) or not done (unmarked)
     */
    public boolean isTaskMarked() {
        return this.isMarked;
    }

    /**
     * @param newStatus assign newStatus (true/false) to this.done
     */
    public void setMark(boolean newStatus) {
        this.isMarked = newStatus;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return String.format("[%s][X] %s", this.symbol, this.description);
        }
        return String.format("[%s][ ] %s", this.symbol, this.description);
    }

    @Override
    public int compareTo(Task o) {
        return this.getScheduledDateAndTime().compareTo(o.getScheduledDateAndTime());
    }
}
