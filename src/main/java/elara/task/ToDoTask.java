package elara.task;

/**
 * Represents a ToDo task, which is a basic task with no specific date or time associated with it.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a new ToDoTask with the specified description.
     * By default, the task is not marked as completed.
     *
     * @param desc The description of the ToDo task.
     */
    public ToDoTask(String desc) {
        super(desc);
    }

    /**
     * Constructs a new ToDoTask with the specified description and completion status.
     *
     * @param desc   The description of the ToDo task.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     */
    public ToDoTask(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        assert !description.isEmpty() : "description should not be empty";
        return "[T]" + super.toString();
    }

    /**
     * Returns the string format of the ToDo task to be saved in a file.
     * The format includes the task type ("T"), the completion status (1 for done, 0 for not done),
     * and the task description.
     *
     * @return A string representation of the ToDo task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
