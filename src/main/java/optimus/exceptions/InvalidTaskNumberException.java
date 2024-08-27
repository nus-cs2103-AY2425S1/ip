package optimus.exceptions;

import optimus.exceptions.OptimusExceptions;

public class InvalidTaskNumberException extends OptimusExceptions {
    public InvalidTaskNumberException() {
        super("A task with this number does not exist.");
    }

    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
