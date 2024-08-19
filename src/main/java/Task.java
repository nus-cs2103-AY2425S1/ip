public class Task {
    private final String description;
    private final String symbol;
    private boolean done;

    public Task(String description, String symbol) {
        this.description = description;
        this.done = false;
        this.symbol = symbol;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * @return the value of this.done, indicating whether the task is done or not done
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
