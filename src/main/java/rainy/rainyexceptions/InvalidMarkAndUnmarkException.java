package rainy.rainyexceptions;
import rainy.tasks.*;
import rainy.database.*;

public class InvalidMarkAndUnmarkException extends Exception{
    public InvalidMarkAndUnmarkException(String errorMessage) {
        super(errorMessage);
    }
}