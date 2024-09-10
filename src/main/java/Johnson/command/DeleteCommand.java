package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private static final String COMMAND_MSG = "Roger that! Taking out the trash:\n";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG +
                index + ": " + Command.taskList.getTask(index - 1).toString());
        String msg = (COMMAND_MSG + index + ": " + Command.taskList.getTask(index - 1).toString());
        Command.taskList.deleteTask(index - 1);
        return msg;
    }
}
