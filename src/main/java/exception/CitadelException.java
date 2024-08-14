package exception;

public abstract class CitadelException extends Exception {

    final String message = "Invalid input: ";
    @Override
    public String toString() {
        return this.message;
    }
}
