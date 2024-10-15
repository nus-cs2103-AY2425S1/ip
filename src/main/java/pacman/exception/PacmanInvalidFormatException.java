package pacman.exception;

public class PacmanInvalidFormatException extends PacmanException {
    public PacmanInvalidFormatException() {
        super("I'm sorry, but I can't find the task name or the time :(");
    }
}
