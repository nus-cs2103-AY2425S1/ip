/** Exception thrown when something goes wrong while writing data to local file. Child of StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class StorageWriteException extends StelleException {
    public StorageWriteException() {
        super("Something went wrong when writing to file!");
    }
}

