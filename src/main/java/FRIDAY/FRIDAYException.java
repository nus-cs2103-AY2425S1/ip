package FRIDAY;

/**
 * Exception is thrown whenever an error occurs with the bot
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class FRIDAYException extends Exception {
    public FRIDAYException(String message) {
        super(message);
    }
}
