public class EmptyTodoException extends AlexException {
    public EmptyTodoException() {
        super("Oops! Description of a todo cannot be empty!");
    }
}