package Exceptions;

public class DavidUnknownActionException extends DavidException{

    public DavidUnknownActionException() {

    }

    @Override
    public String toString() {
        return "I don't know what you mean :( Please enter a valid command followed by an appropriate action.";
    }
}
