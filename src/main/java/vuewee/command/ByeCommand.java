package vuewee.command;

import vuewee.EndProgramException;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to exit the program.
 */
class ByeCommand extends Command {
    /**
     * Executes the ByeCommand.
     *
     * @param ui       the user interface for displaying messages
     * @param taskList the task list to perform operations on
     * @param parser   the command parser for parsing user input
     */
    @Override
    public void execute(TaskListUi ui, TaskList taskList, CommandParser parser) {
        assert ui != null : "UI cannot be null";

        throw new EndProgramException();
    }
}
