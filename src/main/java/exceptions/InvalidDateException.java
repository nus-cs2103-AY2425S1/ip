package exceptions;

public class InvalidDateException extends IllegalInputException {
    public InvalidDateException(String offender) {
        super("I couldn't understand the date you entered:",
                offender,
                "Please follow the format of \"yyyy-mm-dd\".");

    }
}
