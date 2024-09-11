package duck.tasks;

import duck.exceptions.TodoUsageException;

public class Todo extends Task {
    public Todo(String description) throws TodoUsageException {
        super(description);

        if (description == null || description.equals("")) {
            throw new TodoUsageException();
        }
    }

    @Override
    public String getSaveString() {
        return String.format("T,%s,%s", isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
