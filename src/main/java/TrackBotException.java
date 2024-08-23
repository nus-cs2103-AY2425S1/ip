public class TrackBotException extends Exception {
    public TrackBotException(String message) {
        super(message);
    }

    public static TrackBotException invalidFormat(String command, String correctFormat) {
        return new TrackBotException("Invalid format for '" + command + "'. \nCorrect usage: " + correctFormat);
    }
}
