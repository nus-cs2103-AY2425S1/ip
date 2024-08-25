package duke;

public class BobException extends Exception {
    private String error;

    public BobException(String error) {
        this.error = error;
    }

    public String toString() {
        return this.error;
    }
}
