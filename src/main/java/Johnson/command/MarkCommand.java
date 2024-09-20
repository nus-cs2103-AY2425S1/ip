package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends IndexedCommand {
    /**
     * The command word that identifies a MarkCommand instance.
     */
    public static final String COMMAND_WORD = "mark";

    /**
     * The message that is displayed when a MarkCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Bullseye! Bogey down:\n";

    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public String executeCommand() {
        String checkIndex = checkIndex();
        if (checkIndex != null) {
            return checkIndex;
        }

        Command.taskList.markTask(index - 1 );
        assert taskList.getTask(index - 1).getTaskStatus() : "Task should be marked as complete";
        Utilities.OutlineMessage(COMMAND_MSG + index + " :"
                + Command.taskList.getTask(index - 1));
        return COMMAND_MSG + index + " :" + Command.taskList.getTask(index - 1);
    }
}
