package task;

import exception.DukeException;

/**
 * A type of task that an be marked
 */
public class Todo extends Task {
    /**
     * Constructor of task type todo
     * @param description Description of task
     * @throws DukeException An exception that happens due to invalid input
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.isEmpty() || description.equals("todo")) {
            throw new DukeException("todo", "OOPS!!! The description of a todo shouldn't be empty!\n");
        }
    }

    @Override
    public String getString() {
        return "[T]" + super.getString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo other = (Todo) obj;
            return this.description.equals(other.description);
        }
        return false;
    }

    @Override
    public String getStorageFormat() {
        String output = this.isDone ? "1" : "0";
        output += " todo " + description + "\n";
        return output;
    }
}
