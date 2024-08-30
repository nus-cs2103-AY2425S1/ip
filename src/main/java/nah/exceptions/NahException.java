package nah.exceptions;

public class NahException extends Exception {
    /**
     * Constructor
     * @param mess
     */
    public NahException(String mess) {
        super(mess);
    }

    public static class InvalidFileValueException extends NahException {
        /**
         * Constructor
         */
        public InvalidFileValueException() {
            super(" File contain invalid value\n");
        }
    }

    public static class LackDescriptionException extends NahException {
        /**
         * Constructor
         * @param mess
         */
        public LackDescriptionException(String mess) {
            super(mess);
        }
    }

    public static class InvalidTaskNumberException extends NahException {
        public InvalidTaskNumberException() {
            super(" Sorry, there is currently no task in the list to work with\n");
        }

        /**
         * Constructor
         * @param i
         * @param t
         */
        public InvalidTaskNumberException(int i, int t) {
            super(" NAH!!! Nah.Data.Task " + i + " doesn't exist. Please give an number between 1 and " + t + "\n");
        }
    }

}
