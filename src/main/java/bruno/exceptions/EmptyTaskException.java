package bruno.exceptions;

public class EmptyTaskException extends BrunoException {
    public EmptyTaskException() {
        super("You entered an empty task! NOT COOL!");
    }
}
