import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean isDone;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHSS");

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     * @param status The status to change to
     */
    public void setCompletionStatus(boolean status) {
        this.isDone = status;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    /**
     * Returns the string representation of the task when saved.
     * @return a string in the format TYPE | COMPLETION \ NAME | ....
     */
    public String toSaveString() {
        return String.format("%s | %s",
                this.isDone ? "COMPLETE" : "INCOMPLETE",
                this.name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? "X" : " ",
                name);
    }

}
