package panorama.command;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_SHORTHAND = "b";

    @Override
    public Response execute() {
        return new Response("Bye. Hope to see you again soon!", true);
    }
}
