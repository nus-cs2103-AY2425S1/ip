package trackbot;

/**
 * Custom exception class for handling errors in trackbot.
 */
public class TrackBotException extends Exception {
    private final String message;

    /**
     * Constructs a new TrackBotException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TrackBotException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns a TrackBotException indicating an invalid input format.
     *
     * @param command The command that has invalid command.
     * @param correctFormat Show user the correct format.
     * @return A TrackBotException with a detailed message about the invalid format.
     */
    public static TrackBotException invalidFormat(String command, String correctFormat) {
        return new TrackBotException("Invalid format for '" + command + "'. \nCorrect usage: " + correctFormat);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
