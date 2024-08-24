package myapp.blacknut;
public class BlacknutExceptions {

    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    public static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String message) {
            super(message);
        }
    }

    public static class InvalidTaskNumberException extends Exception {
        public InvalidTaskNumberException(String message) {
            super(message);
        }
    }

    public static class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String message) {
            super(message);
        }
    }
}
