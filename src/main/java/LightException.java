public class LightException extends Exception{
    String message;
    LightException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String toString() {
        return this.message;
    }
}
