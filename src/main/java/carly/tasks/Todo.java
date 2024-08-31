package carly.tasks;

import java.text.MessageFormat;

/**
 * Represents a task of type Todo.
 * A Todo task is a basic task without any time constraints.
 */
public class Todo extends Task{

    /** The task type identifier for Todo tasks. */
    private static final String TASK_TYPE = "T";

    /** Constructs a new Todo task with the specified description. */
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Todo task, including its type identifier and status.
     *
     * @return A string formatted as "[T][status] description".
     * @inheritDoc
     */
    @Override
    public String toString() {
        return MessageFormat.format("[{0}]{1}", TASK_TYPE, super.toString());
    }
}
