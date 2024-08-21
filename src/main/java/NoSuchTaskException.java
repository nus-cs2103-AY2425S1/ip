public class NoSuchTaskException extends TaskException{
    public NoSuchTaskException() {
        super("No such task exists.");
    }
}
