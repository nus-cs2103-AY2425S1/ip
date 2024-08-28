package evan.exception;

public class NoSuchTaskException extends Exception {
    public NoSuchTaskException(int taskNumber) {
        super("Oops! Task (" + taskNumber + ") does not exist!");
    }
}
