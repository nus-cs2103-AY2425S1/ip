package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * The command word that identifies a ListCommand instance.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * The message that is displayed when a ListCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Double time, Chief! Here's what you've got on your plate:\n";

    /**
     * The message that is displayed when there are no tasks to list.
     */
    private static final String EMPTY_MSG = "Damn, party's over already? Coast is clear, Chief.\n";

    public ListCommand() {
    }

    @Override
    public String executeCommand() {
        assert Command.taskList != null : "Task list is null";

        if (Command.taskList.taskCount() == 0) {
            Utilities.OutlineMessage(EMPTY_MSG);
            return EMPTY_MSG;
        }

        Utilities.OutlineMessage(COMMAND_MSG + Command.taskList);
        return (COMMAND_MSG + Command.taskList);
    }
}
