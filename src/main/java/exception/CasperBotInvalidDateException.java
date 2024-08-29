package exception;

public class CasperBotInvalidDateException extends CasperBotException {
    /**
     * A custom exception for an invalid date input by the user
     */
    public CasperBotInvalidDateException() {
        super("Date input cannot be read.", "Ensure your date input follows the ISO8601 format!");
    }
}
