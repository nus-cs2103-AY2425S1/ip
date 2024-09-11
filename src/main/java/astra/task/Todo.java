package astra.task;

import astra.AstraException;

/**
 * Represents a task with no deadline.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param name The name of the task.
     * @throws AstraException If the name is empty.
     */
    public Todo(String name) throws AstraException {
        super(name);
    }

    /**
     * Constructor for Todo.
     *
     * @param done Whether the task is done.
     * @param name The name of the task.
     * @throws AstraException If the name is empty.
     */
    public Todo(boolean done, String name) throws AstraException {
        super(done, name);
    }

    @Override
    public String toTextFile() {
        return "T | " + super.toTextFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task other) {
        if (other instanceof Todo) {
            return 0;
        } else {
            return 1;
        }
    }
}
