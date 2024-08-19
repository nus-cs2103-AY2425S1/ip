package Exceptions;

/*
    Exception to handle invalid event task
 */
public class DavidInvalidRangeException extends DavidException{

    public DavidInvalidRangeException(){}

    @Override
    public String toString() {
        return "Please enter a valid range using \"/from\" and \"/to\".";
    }
}
