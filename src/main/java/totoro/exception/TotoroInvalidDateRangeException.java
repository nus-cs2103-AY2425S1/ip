package totoro.exception;

/**
 * Represents an exception that is thrown when an invalid date range is provided
 * in the Totoro chatbot, specifically when the start date is later than the end date
 * <p>
 *     This exception ensures that users provide valid date ranges where the start date
 *     is earlier than the end date for commands involving date ranges
 * </p>
 */
public class TotoroInvalidDateRangeException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s Start date must be earlier than end date", super.toString());
    }
}
