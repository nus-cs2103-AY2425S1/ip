package Components;
import java.util.InputMismatchException;

public class Todo extends Task {

    private Todo(String description) {
        super(description);
    }

    public static Todo createNewTodo(String textString) throws InputMismatchException {
        if (textString.matches("\\s*")) {
            throw new InputMismatchException("Todo description cannot be empty");
        }
        return new Todo(textString.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
