package tasks;

public class TodoTask extends Task {
    private static final String SYMBOL = "T";

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, String note) {
        super(description, note);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTimings() {
        return "";
    }

    @Override
    public String toString() {
        String taskString = String.format("[%s][%s] %s", this.SYMBOL, super.getStatusIcon(), super.description);
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
