public class MissingDeadlineByException extends InputException{
    public MissingDeadlineByException() {
        super("The deadline needs a /by argument!");
    }
}
