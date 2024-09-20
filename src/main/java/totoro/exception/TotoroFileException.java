package totoro.exception;

public class TotoroFileException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s Cannot read from file", super.toString());
    }
}
