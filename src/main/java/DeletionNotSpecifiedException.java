/** Exception thrown when there is no list number given during deletion command. Child of StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class DeletionNotSpecifiedException extends StelleException{
    public DeletionNotSpecifiedException() {
        super("Deletion must specify a task!");
    }
}
