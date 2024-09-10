package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to mark a task as completed in the task list.
 */
class MarkCommand extends Command {
    /**
     * Executes the mark command. Throws an exception if the task index is invalid.
     *
     * @param ui       The user interface for displaying task information.
     * @param taskList The list of tasks.
     * @param parser   The command parser for parsing user input.
     */
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        parser.parse(true, true);
        ui.markTask(parser.getIntParam(), true);
    }
}
