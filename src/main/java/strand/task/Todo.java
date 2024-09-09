package strand.task;

import strand.exception.StrandException;

/**
 * The strand.task.Todo class represents a simple task without any specific time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new strand.Tasks.Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) throws StrandException {
        super(description);
    }

    @Override
    String getType() {
        return "[T]";
    }

    /**
     * Returns the string representation of the strand.Tasks.Todo task
     *
     * @return A string representing the strand.Tasks.Todo task
     */
    @Override
    public String toString() {
        return String.format("%s%s",
                this.getType(),
                super.toString());
    }

    @Override
    public String convertToFileFormat() {
        return String.format("T | %s", super.convertToFileFormat());
    }
}
