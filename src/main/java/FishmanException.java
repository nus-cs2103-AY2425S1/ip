public class FishmanException extends Exception {
    public FishmanException(String message) {
        super(message);
    }

    public class InvalidCommandException extends FishmanException {
        public InvalidCommandException() {
            super("Glub Glub! Please enter a valid command such as 'list' or 'bye' :( ");
        }
    }

    public class EmptyDescriptionException extends FishmanException {
        public EmptyDescriptionException(String command) {
            super("Glub Glub! The description of " + command + " cannot be empty. Please key in some details!");
        }
    }
}
