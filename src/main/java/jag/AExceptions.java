package jag;

/**
 * A custom exception handler that extends the Exception class
 */
public class AExceptions extends Exception {
    protected String errorMessage;

    public AExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return "OOPS!!! " + this.errorMessage;
    }
}
