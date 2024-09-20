package totoro.exception;

public class TotoroFileFormatException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s Unknown task type", super.toString());
    }
}
