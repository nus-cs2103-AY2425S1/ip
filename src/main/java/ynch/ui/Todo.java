package ynch.ui;

/**
 * Represents a Todo task that extends the Task class.
 */

class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param task the description of the todo task
     */
    Todo(String task) {
        super(task);
    }

    /**
     * Constructs a new Todo task with the specified status and description.
     *
     * @param status the completion status of the Todo task (1 for done, 0 for not done)
     * @param task   the description of the Todo task
     */
    Todo(int status, String task) {
        super(status, task);
    }

    /**
     * Returns a string representation of the Todo task, including its completion status.
     *
     * @return a string indicating the type of task and whether it is done or not
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task, including its completion status.
     *
     * @return a string indicating the type of task and whether it is done or not
     */
    @Override
    String toSaveAsString() {
        return "T" + super.toSaveAsString();
    }
}