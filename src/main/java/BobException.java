public class BobException extends Exception{
    public BobException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
