public class InvalidIndexException extends RuntimeException {
    public InvalidIndexException(int capacity, int actual) {
        super(String.format(
            "Invalid index for list. Expected a number from 1 to %d, got %d",
            capacity,
            actual
        ));
    }
}
