package count.action;
import count.exception.CountException;

/**
 * The abstract Action class acts as a blueprint for all commands
 */
public abstract class Action {

    /**
     * Executes the action
     * @return String for Count's UI to print
     * @throws CountException for any child to throw children of CountException
     */
    public abstract String run() throws CountException;

}
