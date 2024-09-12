package potong.task;

import potong.exceptions.IllegalInputPotongException;

/**
 * Represent the To Do Task.
 */
public class ToDoTask extends Task {

    /**
     * Initialise the to do task.
     *
     * @param description Task description.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public ToDoTask(String description) throws IllegalInputPotongException {
        super(description);
    }

    /**
     * Initialise the to do task.
     *
     * @param description Task description.
     * @param isDone Whether the task is done.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public ToDoTask(String description, boolean isDone, String tag) throws IllegalInputPotongException {
        super(description, isDone, tag);
    }

    /**
     * Get the type of the task.
     *
     * @return String "T".
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
