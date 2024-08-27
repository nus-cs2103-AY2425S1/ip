package carly.exception;

public class CarlyUnknownIInputException extends CarlyException {
    public CarlyUnknownIInputException(String action) {
        super("OOPS!! The input '" + action + "' is not valid. Please ensure that you enter a valid command.");
    }

}
