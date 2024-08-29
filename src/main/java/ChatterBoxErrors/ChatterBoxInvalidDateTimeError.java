package ChatterBoxErrors;

/**
 * Represents an error for invalid Date Time format.
 */
public class ChatterBoxInvalidDateTimeError extends ChatterBoxError {
    /**
     * Initialisation for error relating to Date Time format.
     */
    public ChatterBoxInvalidDateTimeError() {
        super("____________________________________________________________\n"
                + "Please input date time in the format of dd/mm/yyyy HHmm (24 hour format)\n"
                + "____________________________________________________________");
    }
}
