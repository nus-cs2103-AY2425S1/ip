package knight2103.tasks;

public class TodoTask extends Task {
    /**
     * Constructs a task Todo object which contains a description of the task.
     * The object by default has the completion status set as not done.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toStringInFile() {
        return "T " + super.toStringInFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}