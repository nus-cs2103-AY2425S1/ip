package cypherchatbot;

public class CypherException extends Exception {
    private String message;
    public CypherException(String msg) {
        super(msg);
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
