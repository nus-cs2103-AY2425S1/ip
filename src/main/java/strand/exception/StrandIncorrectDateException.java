package strand.exception;

/**
 * Thrown when the date provided is not in correct format.
 */
public class StrandIncorrectDateException extends StrandException {
    protected String date;
    public StrandIncorrectDateException(String date) {
        this.date = date;
    }
    @Override
    public String getMessage() {
        return this.date + " is not a valid date " + super.getMessage()
                + "\nPlease include a date of the correct format (e.g. 2/12/2019 1800)";
    }
}
