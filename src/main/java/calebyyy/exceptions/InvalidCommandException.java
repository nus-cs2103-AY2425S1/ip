package calebyyy.exceptions;

public class InvalidCommandException extends CalebyyyException {
    public InvalidCommandException() {
        super("Brother ur command is nonsense!!!");
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
