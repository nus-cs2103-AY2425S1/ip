package Exceptions;

public class DavidInvalidTaskException extends DavidException{

    public DavidInvalidTaskException(){}

    @Override
    public String toString() {
        return "No such task! You can use \"list\" to return a list of valid tasks.";
    }
}
