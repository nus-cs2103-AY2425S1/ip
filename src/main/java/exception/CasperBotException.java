package exception;

/**
 * The parent class for all CasperBotExceptions
 */
public class CasperBotException extends Exception {
    private String solution;
    protected CasperBotException(String message, String solution) {
        super(message);
        this.solution = solution;
    }

    /**
     * Returns the exception message to be displayed to the user
     */
    @Override
    public String getMessage() {
        return String.format("Problem: %s%s%s", super.getMessage(), System.lineSeparator(), this.solution);
    }
}
