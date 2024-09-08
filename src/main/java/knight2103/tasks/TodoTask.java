package knight2103.tasks;

public class TodoTask extends Task {
    private static final String TODO_IDENTIFIER = "T";
    /**
     * Constructs a task Todo object which contains a description of the task.
     * The object by default has the completion status set as not done.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toStringInFile() {
        return String.format("%s %s", TODO_IDENTIFIER, super.toStringInFile());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TODO_IDENTIFIER, super.toString());
    }
}