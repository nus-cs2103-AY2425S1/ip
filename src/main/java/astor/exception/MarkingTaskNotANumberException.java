package astor.exception;

public class MarkingTaskNotANumberException extends AstorException {
    public MarkingTaskNotANumberException() {
        super("Please indicated clearly which astor.task to mark!");
    }
}
