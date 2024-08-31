package exceptions;

public class InvalidDeadlineFormatException extends HimException {
    public InvalidDeadlineFormatException() {
        super("Deadlines need a description and a due date!\n" +
                "Use the format: \"deadline [description] /by [due date] /at [due time]\"\n" +
                "Note: due times are optional!");
    }
}
