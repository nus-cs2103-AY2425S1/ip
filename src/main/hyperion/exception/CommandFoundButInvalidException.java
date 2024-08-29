package exception;

public class CommandFoundButInvalidException extends Exception {
    public CommandFoundButInvalidException(String input) {
        super(input);
    }
}
