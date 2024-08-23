public class NoSuchTaskException extends Exception {
    public NoSuchTaskException(String message, TaskManager taskManager) {
        super(message);
    }
}
