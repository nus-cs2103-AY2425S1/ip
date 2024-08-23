public class WrongInputException extends Exception {
    private final String errorMessage;
    public WrongInputException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
