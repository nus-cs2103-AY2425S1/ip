/**
 * ByeCommand represents the command to exit the chatbot
 */
public class ByeCommand extends Command {

    /** Prefix used to invoke the bye command **/
    public static final String COMMAND_PREFIX = "bye";

    public ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) {
        ui.showText("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
