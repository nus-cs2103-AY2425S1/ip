package chatterboxerrors;

/**
 * Represents an error where a save file cannot be found.
 */
public class ChatterBoxDataFileError extends ChatterBoxError {
    /**
     * Initialisation of error with the file in question.
     * @param fileName Name of file that cannot be found.
     */
    public ChatterBoxDataFileError(String fileName) {
        super("____________________________________________________________\n"
                + "The file " + fileName + " is not found\n"
                + "____________________________________________________________");
    }

    /**
     * Initialisation of error of a generic file.
     */
    public ChatterBoxDataFileError() {
        super("""
              ____________________________________________________________
              The file is not found
              ____________________________________________________________""");
    }
}
