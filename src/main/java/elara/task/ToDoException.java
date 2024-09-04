public class ToDoException extends InvalidInputException {
    public ToDoException() {
        super("The description of a todo cannot be empty! Please append the description of your todo task.");
    }
}
