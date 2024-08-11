package exception;

public class SkibidiSigmaUnknownCommandException extends SkibidiSigmaException {
    private String command;

    public SkibidiSigmaUnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s Unknown command: %s", super.toString(), this.command);
    }
}
