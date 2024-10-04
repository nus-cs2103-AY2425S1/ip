package sora.Tasks;

import java.util.List;

/**
 * Todo is a task
 */
public class ToDo extends Task {
    /**
     * Constructs a new Todo Task.
     *
     * @param description The description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public List<String> getTaskDetails() {
        return List.of("T", getStatus(), description);
    }
}
