package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * The ListCommand class represents a command to list all tasks. It extends the
 * Command class and implements the execute method.
 */
class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the UI.
     *
     * @param ui       the TaskListUI object used to display tasks
     * @param taskList the TaskList object containing the tasks
     * @param parser   the CommandParser object used to parse commands
     */
    @Override
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "Task list cannot be null";
        assert parser != null : "Parser cannot be null";

        ui.displayTasks();
    }
}
