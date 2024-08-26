package count.exception;

public class CountException extends Exception {
    String msg;
    public CountException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }
}
