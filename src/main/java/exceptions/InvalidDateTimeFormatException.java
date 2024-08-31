package exceptions;

public class InvalidDateTimeFormatException extends HimException {
    public InvalidDateTimeFormatException() {
        super("Invalid Date or Time format!\nDate format: yyyy-MM-dd\nTime format: HH:mm");
    }
}
