package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to find a task by description.
 */
class FindCommand extends Command {
    /**
     * Executes the FindCommand.
     *
     * @param ui       the user interface for displaying messages
     * @param taskList the task list to perform operations on
     * @param parser   the command parser for parsing user input
     */
    @Override
    public void execute(TaskListUi ui, TaskList taskList, CommandParser parser) {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "Task list cannot be null";
        assert parser != null : "Parser cannot be null";

        parser.parse(true);
        String keyword = parser.getDescription();
        TaskList matchingTasks = taskList.findTasks(keyword);
        ui.displayTasks(matchingTasks);
    }
}
