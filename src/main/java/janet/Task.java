package janet;

import java.time.LocalDate;

/**
 * Represents a Task
 */
public class Task implements Comparable<Task> {
    private final String description;
    private final String symbol;
    private boolean done;

    public Task(String description, String symbol) {
        this.description = description;
        this.done = false;
        this.symbol = symbol;
    }

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

    @Override
    public int compareTo(Task o) {
        return this.getScheduledDate().compareTo(o.getScheduledDate());
    }
}
