package julie.command;
import julie.misc.UI;
import julie.task.Task;
import java.util.List;

/**
 * The command that handles the closing of the app.
 */
public class ByeCommand extends Command {
    /**
     * Public constructor for the ByeCommand.
     * @param commandString The string to be processed.
     */
    public ByeCommand(String commandString) {
        super(commandString);
        this.isExit = true;
    }
    @Override
    public void run(List<Task> taskList) {
        UI.wrappedLinePrint("Bye!! See you soon!!");
    }
}
