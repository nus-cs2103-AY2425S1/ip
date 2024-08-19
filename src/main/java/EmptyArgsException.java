public class EmptyTodoArgException extends InputException {
    public EmptyTodoArgException() {
        super("The todo description cannot be empty!");
    }
}
