package carly.exception;

public class CarlyMissingDateTimeException extends CarlyException {
    public CarlyMissingDateTimeException(String missingCommand) {
        super("No " + missingCommand  + " detected :(. Please reenter.");
    }

}
