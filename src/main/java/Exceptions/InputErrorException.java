package Exceptions;

public class InputErrorException extends Exception {
    public InputErrorException() {
        super("Erm...What the sigma are you on about?");
    }

    public InputErrorException(String message) {
        super(message);
    }

}
