package mryapper.exception;

public class InvalidSyntaxException extends Exception {

    public InvalidSyntaxException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return " Your syntax is incorrect!\n" + super.getMessage();
    }
}
