package broski.task;


/**
 * Class that holds functionality for todos.
 */
public class Todo extends Task {

    /**
     * Constructs a new task that only has a description.
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringSave() {
        int indicator = this.isDone ? 1 : 0;
        return "T | " + indicator + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
