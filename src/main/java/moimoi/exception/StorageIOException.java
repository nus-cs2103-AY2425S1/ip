package moimoi.exception;

/**
 * Represents an exception related to storage I/O.
 */
public class StorageIOException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "there's something VERY wrong with storage I/O...\n"
                + "Sorry master, I'm afraid I'm unable to save tasks for you TT";
    }

}
