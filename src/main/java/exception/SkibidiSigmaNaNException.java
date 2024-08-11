package exception;

public class SkibidiSigmaNaNException extends SkibidiSigmaException {
    @Override
    public String toString() {
        return String.format("%s Task number is not numeric. Please enter a number", super.toString());
    }
}
