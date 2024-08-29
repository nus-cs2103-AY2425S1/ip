package greetbot;

public class RandomInputException extends Exception {

    final private String MESSAGE;

    public RandomInputException(String message) {
        super();
        this.MESSAGE = message;
    }

    public String getMessage() {
        return this.MESSAGE;
    }
}