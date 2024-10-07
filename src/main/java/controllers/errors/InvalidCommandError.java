package controllers.errors;

public class InvalidCommandError extends Exception {

    public InvalidCommandError(String msg) {
        super(msg);
    }

}