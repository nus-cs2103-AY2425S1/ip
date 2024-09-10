package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to unmark a task in the task list.
 */
class UnmarkCommand extends Command {
    /**
     * Executes the unmark command.
     *
     * @param ui       The user interface for displaying the task list.
     * @param taskList The task list containing the tasks.
     * @param parser   The command parser for parsing user input.
     */
    @Override
    public void execute(TaskListUi ui, TaskList taskList, CommandParser parser) {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "Task list cannot be null";
        assert parser != null : "Parser cannot be null";

        parser.parse(true, true);
        ui.markTask(parser.getIntParam(), false);
    }
}
