package conversage.task;

import conversage.exception.ConverSageException;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param taskDesc the description of the task.
     * @throws ConverSageException if the task description is invalid.
     */
    public ToDo(String taskDesc) throws ConverSageException {
        super(taskDesc);

    }

    /**
     * Returns the string representation of the todo in file format.
     *
     * @return The string representation of the todo in file format.
     */
    @Override
    public String toFileFormat() {
        return "conversage.task.ToDo | " + (isDone ? "Done" : "Not Done") + " | " + taskDesc;
    }


    /**
     * Returns the string representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
