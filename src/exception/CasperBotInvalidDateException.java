package exception;

public class CasperBotInvalidDateException extends CasperBotException {
    public CasperBotInvalidDateException() {
        super("Date input cannot be read.", "Ensure your date input follows the ISO8601 format!");
    }
}
