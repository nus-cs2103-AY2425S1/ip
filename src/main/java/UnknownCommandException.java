public class UnknownCommandException extends BottyException {
    public UnknownCommandException(String command) {
        super("I'm sorry, that is not a command I am familiar with. (" + command + ")");
    }
}
