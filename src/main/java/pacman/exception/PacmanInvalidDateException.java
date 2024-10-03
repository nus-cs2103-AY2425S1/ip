package pacman.exception;

public class PacmanInvalidDateException extends PacmanException {
    public PacmanInvalidDateException() {
        super("I'm sorry, but invalid date/time format, it should be YYYY-MM-DD");
    }
}
