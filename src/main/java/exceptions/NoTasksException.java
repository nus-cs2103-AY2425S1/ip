package exceptions;

public class NoTasksException extends Exception {

    public NoTasksException() {
        super("There are no tasks in the list");
    }
}
