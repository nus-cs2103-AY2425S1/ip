package diomon.exception;

public class MissingInputException extends RuntimeException{
    public MissingInputException() {
        super("Missing Arguments");
    }
    public MissingInputException(String msg) {
        super("Missing Arguments - " + msg);
    }
}
