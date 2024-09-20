package exceptions;

/**
 * The `PositionException` class represents an exception for an invalid position in Java.
 */
public class PositionException extends Exception {
    private int pos;

    public PositionException(int pos) {
        this.pos = pos + 1;
    }

    @Override
    public String toString() {
        return String.format("%s is not a valid position", this.pos);
    }
}
