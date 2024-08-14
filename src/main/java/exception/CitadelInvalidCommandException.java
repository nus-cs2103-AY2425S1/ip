package exception;

public class CitadelInvalidCommandException extends CitadelException {
    @Override
    public String toString() {
        return super.toString() + "Command is invalid :(";
    }
}
