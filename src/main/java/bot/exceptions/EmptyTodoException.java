package bot.exceptions;

public class EmptyTodoException extends InvalidTaskDescriptionException {
    public EmptyTodoException() {
        super("Todo cannot be empty");
    }
}
