package exceptions;

public class InvalidTodoFormatException extends HimException {
    public InvalidTodoFormatException() {
        super("Invalid Todo format");
    }
}