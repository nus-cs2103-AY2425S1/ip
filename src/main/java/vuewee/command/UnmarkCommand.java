package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.TaskListUI;

/**
 * Represents a command to unmark a task in the task list.
 */
class UnmarkCommand extends Command {
    /**
     * Executes the unmark command. Throws an exception if the task index is
     * invalid.
     *
     * @param ui       The user interface for displaying the task list.
     * @param taskList The task list containing the tasks.
     * @param parser   The command parser for parsing user input.
     */
    public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
        parser.parse(true, true);
        ui.markTask(parser.getIntParam(), false);
    }
}
