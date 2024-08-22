public class InvalidTodoArgument extends FridayException {
    public InvalidTodoArgument() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
