package exception;

public class InvalidDateFormatException extends BotException {
    public InvalidDateFormatException(String date) {
        super("Invalid date format: " + date + "! Date must be formatted as yyyy-mm-dd.");
    }
}
