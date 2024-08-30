package easton;

public class DateTimeFormatException extends Exception {
    public DateTimeFormatException() {
        super("Wrong DateTime Format!!! Please use the format, d/M/yyyy H:mm (e.g. 29/12/2024 12:00).");
    }
}
