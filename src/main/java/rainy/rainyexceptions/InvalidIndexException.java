package rainy.rainyexceptions;
import rainy.database.*;
import rainy.tasks.*;

public class InvalidIndexException extends Exception {
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}