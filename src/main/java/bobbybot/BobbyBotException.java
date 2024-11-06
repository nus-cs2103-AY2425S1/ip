package bobbybot;

/**
 * Represents an exception in BobbyBot.
 */
public class BobbyBotException extends Exception {
    public BobbyBotException(String message) {
        super(":((( OOPS!!! " + message);
    }
}
