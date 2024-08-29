package ChatterBoxErrors;

/**
 * Represents an error for invalid Date format.
 */
public class ChatterBoxInvalidDateError extends ChatterBoxError {
    /**
     * Initialisation for error relating to Date format.
     */
    public ChatterBoxInvalidDateError() {
        super("____________________________________________________________\n"
                + "Please input date in the format of dd/mm/yyyy\n"
                + "____________________________________________________________");
    }
}
