public class UnknownInputException extends TalkaBotException {

    public UnknownInputException(String str) {
        super("\"" + str + "\"...? What does that mean?");
    }
}
