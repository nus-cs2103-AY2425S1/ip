package chatterboxerrors;

/**
 * Represents an error for invalid Date format.
 */
public class ChatterBoxInvalidDateError extends ChatterBoxError {
    /**
     * Initialisation for error relating to Date format.
     */
    public ChatterBoxInvalidDateError() {
        super("""
              ____________________________________________________________
              Please input date in the format of dd/mm/yyyy
              ____________________________________________________________""");
    }
}
