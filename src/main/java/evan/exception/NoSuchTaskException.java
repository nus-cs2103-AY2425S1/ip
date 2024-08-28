package evan.exception;

public class NoSuchTaskException extends EvanException {
    public NoSuchTaskException(int taskNumber) {
        super("Oops! Task (" + taskNumber + ") does not exist!");
    }
}
