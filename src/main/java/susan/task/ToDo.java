package susan.task;

/**
 * Represents a task that needs to be done without a stipulated deadline.
 * This class is 1 of 3 types of tasks in the Susan task management application.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
