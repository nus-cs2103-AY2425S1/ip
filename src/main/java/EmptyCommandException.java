public class EmptyCommandException extends BottyException {
    public EmptyCommandException() {
        super("Command cannot be empty!");
    }
}
