package screwllum.exception;

public class InvalidDateFormatException extends ScrewllumException {
    public InvalidDateFormatException(String date) {
        super("Your date input: " + date + " does not match the following pattern yyyy-MM-dd or yyyy-M-d");
    }
}
