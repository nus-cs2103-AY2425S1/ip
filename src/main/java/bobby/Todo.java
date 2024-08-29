package bobby;

import bobby.exception.EmptyDescriptionException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static Todo createTodo(String input) throws EmptyDescriptionException {
        String todoDescription = input.substring(4).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(todoDescription);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String taskInFile() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.getDescription());
    }
}
