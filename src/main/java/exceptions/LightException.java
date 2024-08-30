package exceptions;

public class LightException extends Exception{
    String message;
    public LightException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String toString() {
        return this.message;
    }
}
