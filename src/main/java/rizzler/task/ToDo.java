package rizzler.task;

/**
 * Represents a ToDo: a task that the user has to do.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo object.
     * @param todoDesc Text description of the task to be completed.
     */
    public ToDo(String todoDesc) {
        super(todoDesc);
    }

    public ToDo(String todoDesc, boolean isDone) {
        super(todoDesc, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "ToDo";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv();
    }
}
