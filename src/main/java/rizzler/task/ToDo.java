package rizzler.task;

/**
 * Represents a ToDo: a task that the user has to do.
 */
public class ToDo extends Task {
    public static final String TYPE_STRING = "ToDo";

    /**
     * Constructs a ToDo object.
     * @param todoDesc Text description of the task to be completed.
     */
    public ToDo(String todoDesc) {
        super(todoDesc);
    }

    /**
     * Constructs a ToDo object.
     * @param todoDesc Text description of the task to be completed.
     * @param isDone   Boolean to indicate if the task being created has been marked as done.
     */
    public ToDo(String todoDesc, boolean isDone) {
        super(todoDesc, isDone);
    }

    /**
     * Generates a string ready for printing as a description of a <code>Todo</code>.
     * This contains the description of the particular Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getType() {
        return TYPE_STRING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv();
    }
}
