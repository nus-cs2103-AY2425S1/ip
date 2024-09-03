package PHambot.command;

import PHambot.utils.Utilities;

/**
 * Represents a command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean executeCommand() {
        Command.taskList.unmarkTask(index - 1);
        Utilities.OutlineMessage("Unmarked task " + index + ":\n"
            + Command.taskList.getTask(index - 1));
        return true;
    }
}
