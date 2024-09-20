package secondmind;

public class InvalidTaskNumberException extends Exception {
    int invalidTaskNumber;

    public InvalidTaskNumberException() {
        super();
    }

    @Override
    public String toString() {
        return String.format("Warning! Invalid task number entered!");
    }
}