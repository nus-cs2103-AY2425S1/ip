package strand.exception;

/**
 * Exception thrown when the date given is invalid.
 */
public class StandInvalidDateException extends StrandException {
    protected String date;

    public StandInvalidDateException(String date) {
        this.date = date;
    }

    @Override
    public String getMessage() {
        return this.date + " is not a valid date " + super.getMessage()
                + "\nPlease include a date of the correct format (e.g. 2/12/2019 1800)";
    }
}
