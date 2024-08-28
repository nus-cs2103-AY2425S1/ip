package System.Exceptions;

public class InvalidFormatException extends Exception {
    public InvalidFormatException(String format) {
        super("Invalid Format. Expected: " + format);
    }
}
