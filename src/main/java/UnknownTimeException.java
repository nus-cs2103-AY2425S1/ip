public class UnknownTimeException extends TalkaBotException {

    public UnknownTimeException(String action) {
        super("Please let me know when it " + action + " so I can record it for you!");
    }
}
