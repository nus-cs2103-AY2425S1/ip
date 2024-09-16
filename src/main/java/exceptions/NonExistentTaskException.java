package exceptions;

public class NonExistentTaskException extends Exception {

    public NonExistentTaskException() {
        super("Task does not exist");
    }
}
