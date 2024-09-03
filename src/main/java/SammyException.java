public class SammyException extends Exception {
    public SammyException(String message) {
        super(message);
    }
}

class InvalidDescriptionException extends SammyException {
    public InvalidDescriptionException() {
        super("The description is invalid. It cannot be empty.");
    }
}

class InvalidCommandException extends SammyException {
    public InvalidCommandException() {
        super("I'm sorry, your command is not recognized.");
    }
}

class InvalidTaskNumberException extends SammyException {
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}
