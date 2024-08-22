public class MonoBotException extends Exception {
    String message;
    public MonoBotException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
