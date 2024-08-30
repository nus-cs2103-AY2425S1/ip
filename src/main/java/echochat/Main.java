package echochat;
import Exceptions.EmptyDescriptionError;
import Exceptions.InvalidCommandError;

public class Main {
    public static void main (String args[]) throws InvalidCommandError, EmptyDescriptionError {
        Echo echo = new Echo();
        echo.run();
    }

}
