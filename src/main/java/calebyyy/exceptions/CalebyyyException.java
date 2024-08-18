package calebyyy.exceptions;

public class CalebyyyException extends Exception {

    public CalebyyyException(String message) {
        super("" + message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}