public class InvalidMarkingException extends TalkaBotException {

    public InvalidMarkingException(String marking) {
        super("Sorry, what were you trying to " + marking + " again?");
    }
}
