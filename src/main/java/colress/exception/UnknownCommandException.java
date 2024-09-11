package colress.exception;

/**
 * An exception that is thrown when there is an invalid command entered by the user.
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructs an UnknownCommandException with the given error message.
     */
    public UnknownCommandException() {
        super("""
                I do not recognise that command.
                Here are the commands I recognise:
                add: add a new item to your list of tasks.
                check: check off an item to your list of tasks.
                uncheck: uncheck an item to your list of tasks.
                delete: delete an item from your list of tasks.
                list: print out your list of tasks.
                date: print out deadlines and events that occur on a specified date.
                find: print out tasks that contain a specified keyword.
                bye: bid me farewell.
                Try again."""
        );
    }
}
