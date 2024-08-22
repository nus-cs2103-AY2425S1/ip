public class OysterException extends RuntimeException {
    public OysterException(String message) {
        super(String.format("[%s] %s", Oyster.CHATBOT_NAME, message));
    }
}
