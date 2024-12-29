package phenex.task;

/**
 * Abstract class Task which encapsulates a Task for the Phenex to keep track of.
 */
public abstract class Task {
    protected final String name;
    protected boolean isCompleted;
    protected String symbol;
    protected double hoursTaken;
    protected double defaultHours = 0;

    /**
     * Creates a Task.
     * @param name the name of the task.
     * @param symbol the symbol of the task.
     */
    public Task(String name, String symbol) {
        this.name = name;
        this.isCompleted = false;
        this.symbol = symbol;
        this.hoursTaken = defaultHours;
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

    protected void setCompleted(double hoursTaken) {
        this.isCompleted = true;
        this.hoursTaken = hoursTaken;
    }

    protected void setUncompleted() {
        this.isCompleted = false;
        this.hoursTaken = defaultHours;
    }

    protected String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    public double getHoursTaken() {
        return this.hoursTaken;
    }

    @Override
    public String toString() {
        String hoursString = (this.getStatusIcon().equals("X"))
                             ? "\n\t\tHours taken: " + this.hoursTaken
                             : "";
        return "["
                + this.getStatusIcon()
                + "] "
                + this.name
                + "."
                + hoursString;
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
