import java.io.IOException;

public class BadDataFormatException extends IOException {
    public BadDataFormatException(String message) {
        super(message);
    }
}
