package pacman.exception;

public class PacmanNoTaskNameException extends PacmanException {
    public PacmanNoTaskNameException() {
        super("I'm sorry, but I can't find the task name :(");
    }
}
