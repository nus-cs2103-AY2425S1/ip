package PHambot.command;

import PHambot.utils.Utilities;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean executeCommand() {
        Utilities.OutlineMessage("Removed task " + index + ":\n" +
                Command.taskList.getTask(index - 1).toString());
        Command.taskList.deleteTask(index - 1);
        return true;
    }
}
