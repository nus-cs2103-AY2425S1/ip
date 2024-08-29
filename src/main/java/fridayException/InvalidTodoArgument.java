package fridayException;

public class InvalidTodoArgument extends FridayException {
    public InvalidTodoArgument() {
        super("The description of a todo cannot be empty.");
    }
}
