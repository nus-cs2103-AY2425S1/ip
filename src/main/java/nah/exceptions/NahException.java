package nah.exceptions;

/**
 * Custom exception class for handling errors in the Nah application.
 */
public class NahException extends Exception {

    public NahException(String mess) {
        super(mess);
    }

    /**
     * Exception for invalid values in the file.
     */
    public static class InvalidFileValueException extends NahException {

        public InvalidFileValueException() {
            super(" File contain invalid value\n");
        }
    }

    /**
     * Exception for missing descriptions.
     */
    public static class LackDescriptionException extends NahException {

        public LackDescriptionException(String mess) {
            super(mess);
        }
    }

    /**
     * Exception for invalid task numbers.
     */
    public static class InvalidTaskNumberException extends NahException {
        public InvalidTaskNumberException() {
            super(" Sorry, there is currently no task in the list to work with\n");
        }


        public InvalidTaskNumberException(int i, int t) {
            super(" NAH!!! Nah.Data.Task " + i + " doesn't exist. Please give an number between 1 and " + t + "\n");
        }
    }

}
