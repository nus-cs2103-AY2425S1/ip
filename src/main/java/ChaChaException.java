public class ChaChaException extends Exception {
    private String msg;
    public ChaChaException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
