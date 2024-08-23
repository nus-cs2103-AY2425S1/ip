public class PositionException extends Exception{

    int pos;
    PositionException(int pos) {
        this.pos = pos;
    }
    @Override
    public String toString() {
        return String.format("%s is not a valid position", this.pos);
    }
}
