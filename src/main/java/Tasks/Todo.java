package Tasks;

import java.util.InputMismatchException;

public class Todo extends Task {

    private Todo(String description) {
        super(description);
    }

    public static Todo createNewTodo(String userInput) throws InputMismatchException {
        if (userInput.matches("\\s*")) {
            throw new InputMismatchException("Todo description cannot be empty");
        } 
        return new Todo(userInput.trim());
    }

    public static Todo createNewTodo(String description, boolean isMarked) {
        Todo newTodo = new Todo(description);
        if (isMarked) {
            newTodo.completeTask();
        }
        return newTodo;
    }

    @Override
    public String formatStringForSaving() {
        return toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
