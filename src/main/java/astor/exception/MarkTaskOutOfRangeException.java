package astor.exception;

public class MarkTaskOutOfRangeException extends AstorException {
    public MarkTaskOutOfRangeException(int numberOfTasks) {
        super("Please enter a valid astor.task number between 1 and " + numberOfTasks);
    }
}
