package janet;

import java.time.LocalDateTime;

/**
 * Represents a Task
 */
public class Task {
    private final String description;
    private final String symbol;
    private boolean done;
    private final LocalDateTime scheduledDateTime;

    public Task(String description, String symbol, LocalDateTime scheduledDateTime) {
        this.description = description;
        this.done = false;
        this.symbol = symbol;
        this.scheduledDateTime = scheduledDateTime;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDateTime;
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
    public boolean isDone() {
        return this.done;
    }

    /**
     * @param newStatus assign newStatus (true/false) to this.done
     */
    public void setDone(boolean newStatus) {
        this.done = newStatus;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[%s][X] %s", this.symbol, this.description);
        }
        return String.format("[%s][ ] %s", this.symbol, this.description);
    }
}
