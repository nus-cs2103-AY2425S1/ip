package sigma.exception;

public class SigmaUnknownCommandException extends SigmaException {
    private String command;

    public SigmaUnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s Unknown command: %s", super.toString(), this.command);
    }
}
