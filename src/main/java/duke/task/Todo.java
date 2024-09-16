package duke.task;

public class Todo extends Task {
    private final String TODO_ICON = "[T]";
    private final String TODO = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_ICON + super.toString();
    }

    /**
     * Returns string representation of the task to be saved in the text file.
     * @return String of Todo
     */
    @Override
    public String toFileString() {
        return TODO + BAR + (isDone ? "1" : "0") + BAR + description;
    }
}
