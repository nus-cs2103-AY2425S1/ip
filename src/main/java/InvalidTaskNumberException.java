public class InvalidTaskNumberException extends Exception {
    int invalidTaskNumber;

    public InvalidTaskNumberException(int invalidTaskNumber) {
        super();
        this.invalidTaskNumber = invalidTaskNumber;
    }

    @Override
    public String toString() {
        return String.format("Warning! Task number %d does not exist!", this.invalidTaskNumber);
    }
}