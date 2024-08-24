public class InvalidEditException extends TalkaBotException {

    public InvalidEditException(String edit) {
        super("Sorry, what were you trying to " + edit + " again?");
    }
}
