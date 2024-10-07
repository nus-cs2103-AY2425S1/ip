package controllers.errors;

public class InvalidInputError extends Exception {

    public InvalidInputError(String msg) {
        super(msg);
    }

}