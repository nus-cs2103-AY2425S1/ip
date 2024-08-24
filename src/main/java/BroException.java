public class BroException extends Exception {
    public BroException(String msg) {
        super(Bro.line + "   " + msg + "\n" + Bro.line);
    }
}
