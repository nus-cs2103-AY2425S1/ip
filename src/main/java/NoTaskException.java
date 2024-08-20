public class NoTaskException extends Exception {
    public NoTaskException() {
        super("Hmm... I don't see any tasks... Please enter the description of the task!");
    }
}
