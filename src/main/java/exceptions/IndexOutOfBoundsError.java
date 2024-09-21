package exceptions;

public class IndexOutOfBoundsError extends RuntimeException {
    public IndexOutOfBoundsError() {
        super("Invalid index. Please try again");
    }
}
