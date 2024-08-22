/** Exception thrown when there is no task under the list number given during a command. Child of StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class NoSuchTaskException extends TaskException{
    public NoSuchTaskException() {
        super("No such task exists.");
    }
}
