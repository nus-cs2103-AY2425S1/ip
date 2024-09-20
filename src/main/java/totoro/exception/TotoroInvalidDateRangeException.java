package totoro.exception;

public class TotoroInvalidDateRangeException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s Start date must be earlier than end date", super.toString());
    }
}
