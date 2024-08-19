public class InvalidInputException extends Exception {
    private String error;

    public InvalidInputException(String error) {
        this.error = error;
    }

    public String toString() {
        return this.error;
    }
}
