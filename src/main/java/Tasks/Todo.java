package Tasks;

import enums.TaskType;

/**
 * Represents a to-do task, which is a basic type of task without any associated time constraints.
 * This class extends the {@link Task} class by setting the task type specifically to TODO.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The textual description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the string representation of this to-do task in a format suitable for file storage.
     * This includes the type indicator 'T', followed by the base task's file format.
     *
     * @return The formatted string suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }

    /**
     * Provides a string representation of the to-do task, including its status and description,
     * prefixed with '[T]' to indicate it is a to-do type task.
     *
     * @return The detailed string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
