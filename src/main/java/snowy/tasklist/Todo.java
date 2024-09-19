package snowy.tasklist;

/**
 * Todo class is a task that has a description.
 *
 * The Todo class extends the Task class and adds a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + this.getTags();
    }
}
