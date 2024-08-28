public class BroException extends Exception {
    public BroException(String msg) {
        super(Ui.line + "   " + msg + "\n" + Ui.line);
    }
}
