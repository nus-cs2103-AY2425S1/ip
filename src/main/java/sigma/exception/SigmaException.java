package sigma.exception;

public abstract class SigmaException extends Exception {
    @Override
    public String toString() {
        return "Error:";
    }
}
