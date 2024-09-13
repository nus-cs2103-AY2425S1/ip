package exceptions;

public class PositionException extends Exception {
    private int pos;

    public PositionException(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return String.format("%s is not a valid position", this.pos);
    }
}
