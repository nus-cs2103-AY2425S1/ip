public class WrongInputException extends Exception {
    public WrongInputException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
