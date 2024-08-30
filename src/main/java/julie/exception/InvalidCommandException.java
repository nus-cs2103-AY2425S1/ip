package julie.exception;

public class InvalidCommandException extends JulieException {
    public InvalidCommandException(String message) {
        super(String.format("Omg i'm so sorry I don't know what %s means", message));
    }
}
