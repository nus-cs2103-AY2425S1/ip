public class RandomInputException extends Exception {

    final private String message;

    public RandomInputException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}