public class JeffException extends Exception {
    private String message;

    public JeffException(String message) {
        super(message);
        this.message = message;
    }

    public JeffException() {
        super();
        this.message = "Sorry, I don't know what this means.";
    }

    @Override
    public String toString() {
        return this.message;
    }
}
