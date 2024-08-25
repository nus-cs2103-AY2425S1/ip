package moimoi.exception;

/**
 * Represents an exception related to corrupted storage.
 */
public class StorageCorruptedException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "seems like file for storage has been WrEckEd (corrupted)...\n"
                + "Sorry master, I'm afraid I couldn't load any existing tasks for you TT\n";
    }

}
