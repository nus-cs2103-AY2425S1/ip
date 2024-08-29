package jeriel.util;
import jeriel.command.*;
import jeriel.task.*;

public class JerielException extends Exception {
    public JerielException(String message) {
        super(message);
    }

    public JerielException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
