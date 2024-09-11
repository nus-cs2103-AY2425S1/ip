package knight2103.tasks;

/**
 * Models a ToDo Task.
 */
public class TodoTask extends Task {
    private static final String TODO_IDENTIFIER = "T";

    /**
     * Constructs a task object that is of a Todo nature. It contains a description of the task.
     * The object by default has the completion status set as not done.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public TaskType showTaskType() {
        return TaskType.TODO;
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