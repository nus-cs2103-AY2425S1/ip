package jeff.exceptions;

public class LineCorruptedException extends FileCorruptedException{
    public LineCorruptedException(String message) {
        super(message);
    }
}
