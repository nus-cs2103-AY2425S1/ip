package dude.exception;

/**
 * Thrown to indicate that the data is found to be corrupted.
 */
public class DudeCorruptedDataException extends DudeException {

    /**
     * Constructs a DudeCorruptedDataException with no detail message.
     */
    public DudeCorruptedDataException() {
        super("Your data is corrupted, I will just ignore them.");
    }
}
