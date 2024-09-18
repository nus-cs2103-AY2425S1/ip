package julie.command;

import java.util.List;

import julie.misc.Storage;
import julie.task.Task;


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
    public String run(List<Task> taskList, Storage storage) {
        return "Bye!! See you soon!!\n This command has been deprecated.";
    }
}
