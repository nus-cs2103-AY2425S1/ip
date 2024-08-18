package calebyyy.exceptions;

public class InvalidArgumentException extends CalebyyyException {
    public InvalidArgumentException() {
        super("Your argument can't be empty!!!");
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
