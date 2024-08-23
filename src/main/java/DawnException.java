public class DawnException extends Exception{
    String msg;
    public DawnException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
