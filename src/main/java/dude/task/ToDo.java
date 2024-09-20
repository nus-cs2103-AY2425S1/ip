package dude.task;

/**
 * Represents a task with only description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task's data into a string format for saving to a file.
     *
     * @return A string representing the ToDo task's data for saving.
     */
    @Override
    public String taskToStringData() {
        return "T" + super.taskToStringData();
    }

    /**
     * Returns a string representation of the ToDo task, including its status and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this ToDo object.
     *
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        boolean isToDo = obj instanceof ToDo;
        boolean isSameDescription = super.equals(obj);

        return isToDo && isSameDescription;
    }
}
