package main.java.exception;

public class CasperBotMissingInputException extends CasperBotException {
    public CasperBotMissingInputException(String key, String command) {
        super("Missing " + key, key + " is required for " + command + " command");
    }
}
