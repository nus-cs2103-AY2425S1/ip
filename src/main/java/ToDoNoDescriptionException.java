public class ToDoNoDescriptionException extends TaskException {
    public ToDoNoDescriptionException() {
        super("The description of a todo cannot be empty!");
    }
}
