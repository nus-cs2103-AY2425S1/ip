package exception;

/**
 * A custom exception for a missing field that is required
 */
public class CasperBotMissingInputException extends CasperBotException {
    public CasperBotMissingInputException(String key, String command) {
        super("Missing " + key, key + " is required for " + command + " command");
    }
}
