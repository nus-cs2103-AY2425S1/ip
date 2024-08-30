package knight2103.tasks;

public class Todo extends Task {
    /**
     * Constructs a task Todo object which contains a description of the task.
     * The object by default has the completion status set as not done.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveToFileFormat() {
        return "T " + super.saveToFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}