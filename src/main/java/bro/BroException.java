package bro;

public class BroException extends Exception {
    public BroException(String msg) {
        super("   " + msg + "\n");
    }
}
