/** Exception thrown when the command given by user does not exist. Child of StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class WrongCommandException extends StelleException {
    public WrongCommandException() {
        super("I'm sorry, I don't know what that means...");
    }
}

