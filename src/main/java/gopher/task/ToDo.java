package gopher.task;

import gopher.exception.InvalidTokenException;
import gopher.parser.Parser;

/**
 * Represents the todo Task.
 * Only includes task description.
 */
public class ToDo extends Task {
    /**
     * Constructor for todo class
     *
     * @param name name of the task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public void update(String[] tokens) throws InvalidTokenException {
        // Extract task name from the given tokens
        String taskName = Parser.parseUpdateTodoTaskCommand(tokens)[0];
        if (!taskName.isEmpty()) {
            this.name = taskName;
        }
    }

    @Override
    public String getSaveMessage() {
        return String.format("T | %s | %s",
                getStatusIcon(),
                this.name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
