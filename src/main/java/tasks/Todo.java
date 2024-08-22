package tasks;

import exceptions.TodoUsageException;

public class Todo extends Task {
    public Todo(String description) throws TodoUsageException {
        super(description);

        if (description == null || description.equals("")) {
            throw new TodoUsageException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
