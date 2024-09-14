package calebyyy.exceptions;

public class InvalidDateException extends CalebyyyException {

    public InvalidDateException() {
        super("Brother ur date needs to be in the format d/M/yyyy HHmm!!!!");
    }
    
    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
