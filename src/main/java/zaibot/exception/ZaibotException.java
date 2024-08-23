package zaibot.exception;

public class ZaibotException extends Exception {
    public ZaibotException(String message) {
        super(message + "\n");
    }
}
