package gopher.task;

import gopher.exception.InvalidTokenException;

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
        StringBuilder taskName = new StringBuilder();
        for (int i = 2; i < tokens.length; i++) {
            // If other tasks tokens are used, remind user that
            // he/she may be updating the undesired task
            if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("todo", tokens[i]);
            }

            // Append the token if everything is fine
            taskName.append(tokens[i]);
            if (i < tokens.length - 1) {
                taskName.append(" ");
            }
        }

        this.name = taskName.toString();
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
