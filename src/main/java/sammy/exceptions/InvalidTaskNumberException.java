package sammy.exceptions;

import sammy.SammyException;

public class InvalidTaskNumberException extends SammyException {
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}