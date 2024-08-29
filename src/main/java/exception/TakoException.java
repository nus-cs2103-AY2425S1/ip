package exception;

public class TakoException extends Exception {

    String message;

    public TakoException(String message) {
        this.message = message;
    }
    public String message() {
        return this.message;
    }
}
