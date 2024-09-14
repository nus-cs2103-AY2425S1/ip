package calebyyy.exceptions;

public class InvalidTaskNumberException extends CalebyyyException {
    public InvalidTaskNumberException() {
        super("Brother ur task number is invalid!!!");
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
    
}
