public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        super("Meow!! Description of a todo cannot be empty :(");
    }
}