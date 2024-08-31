package mryapper.exception;

public class InvalidFileDataException extends Exception {
    private String errorMessage;

    public InvalidFileDataException(String errorMessage) {
        super(errorMessage);
    }
}
