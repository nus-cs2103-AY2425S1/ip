package oyster.exceptions;

public class DateFormatException extends OysterException {
    public DateFormatException() {
        super(String.format("[%s] %s", "Invalid date", "Please input in (dd/mm/yyyy) format!"));
    }
}
