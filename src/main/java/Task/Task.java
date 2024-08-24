package Task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String DELIMITER = "|";

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected String wrapInQuotes(String str) {
        return "\"" + str + "\"";
    }

    protected String getTaskType() {
        return "Task";
    }

    public String formatToCSV() {
        String[] arr = new String[]{
                wrapInQuotes(this.getStatusIcon()),
                wrapInQuotes(this.getTaskType()),
                wrapInQuotes(this.description)
        };
        return String.join(DELIMITER, arr);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}