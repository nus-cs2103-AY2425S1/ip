package atreides.task;

/**
 * Represents a ToDo task in the task list.
 *
 * A ToDo task is a basic type of task that contains only a description, without any time-related attributes.
 * Inherits from the Task class, leveraging its base functionalities for handling tasks.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String write() {
        return "T" + super.write();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
