package elysia.task;

/**
 * Represents a ToDo task with a description.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDo task with the description.
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToTxt() {
        int i = this.isDone ? 1 : 0;
        return "T | " + i + " | " + this.description;
    }
}
