package nah.exceptions;

public class NahException extends Exception {

    public NahException(String mess) {
        super(mess);
    }

    public static class InvalidFileValueException extends NahException {

        public InvalidFileValueException() {
            super(" File contain invalid value\n");
        }
    }

    public static class LackDescriptionException extends NahException {

        public LackDescriptionException(String mess) {
            super(mess);
        }
    }

    public static class InvalidTaskNumberException extends NahException {
        public InvalidTaskNumberException() {
            super(" Sorry, there is currently no task in the list to work with\n");
        }


        public InvalidTaskNumberException(int i, int t) {
            super(" NAH!!! Nah.Data.Task " + i + " doesn't exist. Please give an number between 1 and " + t + "\n");
        }
    }

}
