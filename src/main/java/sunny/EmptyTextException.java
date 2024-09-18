package sunny;

/**
 * Returns exception message when user did not give an input
 */
public class EmptyTextException extends Exception {
    public EmptyTextException() {
        super("Please specify a task");
    }

    @Override
    public String toString() {
        return "Please specify a task? :) \n";
    }
}
