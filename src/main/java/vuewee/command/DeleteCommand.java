package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to delete a task from the task list.
 */
class DeleteCommand extends Command {
    /**
     * Executes the delete command.
     *
     * @param ui       The user interface for displaying messages.
     * @param taskList The task list containing the tasks.
     * @param parser   The command parser for parsing user input.
     */
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        parser.parse(true, true);
        ui.deleteTask(parser.getIntParam());
    }
}
