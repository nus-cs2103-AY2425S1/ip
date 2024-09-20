package totoro.exception;

public class TotoroUnknownCommandException extends TotoroException {
    private String command;

    public TotoroUnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s I don't know what %s means :(", super.toString(), this.command);
    }
}
