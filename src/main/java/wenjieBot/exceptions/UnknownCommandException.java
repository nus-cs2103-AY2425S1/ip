package wenjieBot.exceptions;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("test");
    }

    @Override
    public String getMessage() {
        return "gg bro idk what command you want me to do, i only got \n " +
                "'todo', 'event', 'deadline, 'list', 'delete', 'mark', 'unmark' and 'bye'";
    }
}
