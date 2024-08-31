package exception;
import prince.Prince;

/**
 * Exception raised for when commands are not given correcntly
 *
 * This exception is typically raised when a user attempts to execute an unknown command.
 *
 * eg. of incomplete commands: find tasks, give tasks
 */

public class UnknownWordException extends Exception {
    public UnknownWordException(String message) {
        super(message);
    }
}

// just create a method in prince, called exceptionscaller then for each issue throw the speciifc exception
// have a exception for (not knowing a particular command)
// have an exception for (only giving the first task type)
// try splitting, if cnanot split then raises error, i wanna go to a method first that will check
// if the word is either of the types, then throw incomeplete exception
// if not throw unknown exception