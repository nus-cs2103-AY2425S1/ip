package david.exceptions;


/**
 * Invalid time exception class for David class
 */
public class DavidInvalidTimeException extends DavidException {
    public DavidInvalidTimeException() {};
    @Override
    public String toString() {
        return "Please ensure that the time inputted is after the current time. If you are trying"
                + " to input an event task, make sure \"/from\" field is a valid time before  \"/to\" field";
    }

}

