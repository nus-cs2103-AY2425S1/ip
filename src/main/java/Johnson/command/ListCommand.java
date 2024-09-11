package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    private static final String COMMAND_MSG = "Double time, Chief! Here's what you've got on your plate:\n";

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
