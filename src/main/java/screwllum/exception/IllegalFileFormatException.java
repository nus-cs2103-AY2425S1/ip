package screwllum.exception;

/**
 * An Exception specific to the Screwllum bot for loading of save files.
 */
public class IllegalFileFormatException extends ScrewllumException {
    /**
     * Constructs an exception with the given message.
     */
    public IllegalFileFormatException(String message) {
        super("How peculiar, the file being loaded contains " + message + " which is not the right format.\n"
            + "Please delete the existing file so that I can write a new one for you!");
    }
}
