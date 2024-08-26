package echochat;
import main.java.Exceptions.EmptyDescriptionError;
import main.java.Exceptions.InvalidCommandError;

public class Main {
    public static void main (String args[]) throws InvalidCommandError, EmptyDescriptionError {
        Echo echo = new Echo();
        echo.run();
    }

}
