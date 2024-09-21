package flychat.tasks;

import java.util.InputMismatchException;

/**
 * Represents the Todo task type.
 */
public class Todo extends Task {

    private Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo task.
     *
     * @param description A string containing the description for the todo.
     * @param isMarked A boolean indicating whether the todo task is marked.
     * @return New Todo object.
     * @throws InputMismatchException If description is empty.
     */
    public static Todo createNewTodo(String description, boolean isMarked) throws InputMismatchException {
        if (description.equals("")) {
            throw new InputMismatchException("Please ensure that the input contains a description TT");
        }

        Todo newTodo = new Todo(description);
        if (isMarked) {
            newTodo.completeTask();
        }
        return newTodo;
    }

    @Override
    public String formatStringForSaving() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
