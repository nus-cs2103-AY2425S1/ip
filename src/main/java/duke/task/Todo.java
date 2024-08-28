package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string representation of the task to be saved in the text file.
     * @return String of Todo
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
