package main.java;
public class AronaException extends Exception {
    private String msg;
    public AronaException(String message) {
        super(message);
        msg = message;
    }

    public String getMessage() {
        return msg;
    }
}
