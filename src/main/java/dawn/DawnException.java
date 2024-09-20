package dawn;

public class DawnException extends Exception{
    String msg;

    /**
     * Creates an instance of DawnException with the specified error message
     *
     * @param msg
     */
    public DawnException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
