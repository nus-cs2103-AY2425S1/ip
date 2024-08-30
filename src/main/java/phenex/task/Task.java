package phenex.task;

public abstract class Task {
    protected final String name;
    protected boolean isCompleted;
    protected String symbol;

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
}
