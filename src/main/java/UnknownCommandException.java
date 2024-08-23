public class UnknownCommandException extends DukeException{
    UnknownCommandException() {
        super("test");
    }

    @Override
    public String toString() {
        return "gg bro idk what command you want me to do, i only got \n " +
                "'todo', 'event', 'deadline, 'list', 'delete', 'mark', 'unmark' and 'bye'";
    }
}
