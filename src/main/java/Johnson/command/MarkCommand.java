package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private static final String COMMAND_MSG = "Bullseye! Bogey down:\n";
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeCommand() {
        Command.taskList.markTask(index - 1 );
        Utilities.OutlineMessage(COMMAND_MSG + index + " :"
                + Command.taskList.getTask(index - 1));
        return COMMAND_MSG + index + " :" + Command.taskList.getTask(index - 1);
    }
}
