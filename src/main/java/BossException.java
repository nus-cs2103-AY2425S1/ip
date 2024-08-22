public class BossException extends Exception {
    public BossException(String message) {
        super(message);

    }

    @Override
    public String toString() {
        return getMessage();
    }
}
