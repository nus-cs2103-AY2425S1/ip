package phenex.task;

/**
 * Abstract class Task which encapsulates a Task for the Phenex to keep track of.
 */
public abstract class Task {
    protected final String name;
    protected boolean isCompleted;
    protected String symbol;

    /**
     * Creates a Task.
     * @param name the name of the task.
     * @param symbol the symbol of the task.
     */
    public Task(String name, String symbol) {
        this.name = name;
        this.isCompleted = false;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getSymbol() {
        return this.symbol;
    }

    protected void setCompleted() {
        this.isCompleted = true;
    }

    protected void setUncompleted() {
        this.isCompleted = false;
    }

    protected String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    @Override
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + this.name;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            Task compared = (Task) object;
            return this.name.equals(compared.name);
        }
        return false;
    }
}
