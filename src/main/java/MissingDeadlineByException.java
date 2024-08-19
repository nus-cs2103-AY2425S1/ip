public class MissingDeadlineArgException extends InputException{
    public MissingDeadlineArgException() {
        super("The deadline needs a /by argument!");
    }
}
