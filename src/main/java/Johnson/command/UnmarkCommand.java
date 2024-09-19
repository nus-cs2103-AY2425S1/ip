package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to mark a task as incomplete.
 */
public class UnmarkCommand extends IndexedCommand {

    /**
     * The command word that identifies an UnmarkCommand instance.
     */
    public static final String COMMAND_WORD = "unmark";

    /**
     * The message that is displayed when an UnmarkCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Damn, must've missed. Watch out for this one:\n";

    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    public String executeCommand() {
        String checkIndex = checkIndex();
        if (checkIndex != null) {
            return checkIndex;
        }

        Command.taskList.unmarkTask(index - 1);

        assert !taskList.getTask(index - 1).getTaskStatus() : "Task should be marked as incomplete";

        Utilities.OutlineMessage(COMMAND_MSG +
                index + ":  " + Command.taskList.getTask(index - 1));

        return COMMAND_MSG + index + ":  " + Command.taskList.getTask(index - 1);
    }
}
