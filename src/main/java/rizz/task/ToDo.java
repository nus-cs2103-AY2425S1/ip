package rizz.task;

/**
 * Represents a ToD0 task which is a specific type of Task.
 * A ToD0 task only contains a description and completion status.
 */
public class ToDo extends Task{

    /**
     * Constructs a new ToD0 task.
     *
     * @param text The description of the task.
     * @param isDone The completion status of the task. If true, the task is marked as done.
     */
    public ToDo(String text, boolean isDone) {
        super(text, isDone);
    }


    /**
     * Exports the ToD0 task as a formatted string for saving.
     * The format will be: "T | <isDone> | <text>"
     * where <isDone> = true -> 1, <isDone> = true -> 0
     *
     * @return The string representation of the ToD0 task for file storage.
     */
    @Override
    public String export() {
        return "T | " + (isDone ? "1" : "0") + " | " + text;
    }


    /**
     * Returns a string representation of the ToD0 task.
     * The format will be: "[T][X] <text>" if the task is done,
     * or "[T][ ] <text>" if the task is not done.
     *
     * @return The string representation of the ToD0 task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
