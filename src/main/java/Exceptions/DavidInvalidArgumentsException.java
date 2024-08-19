package Exceptions;

public class DavidInvalidArgumentsException extends DavidException{
    public DavidInvalidArgumentsException() {}


    @Override
    public String toString() {
        return "Please enter valid arguments.";
    }
}
