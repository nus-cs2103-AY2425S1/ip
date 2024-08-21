public class InvalidIndexException extends Exception {
    InvalidIndexException(String input) {
        super(input + " is an invalid index!");
    }
}
