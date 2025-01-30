package star.task;

/**
 * Represents a Todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     * @param description String that gives the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task with the given description and tag.
     * @param description String that gives the description of the Todo task.
     * @param tag String that gives the tag applied to the Todo task.
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    @Override
    public String getOutput() {
        return hasTag() ? String.format("T | %d | %s | #%s", isDone ? 1 : 0, description, super.tag)
                : String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return hasTag() ? "[T]" + super.toString() + String.format(" #%s", super.tag)
                : "[T]" + super.toString();
    }
}
