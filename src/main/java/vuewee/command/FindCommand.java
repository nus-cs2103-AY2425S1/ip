package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.parser.description.StringDescriptionParser;
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
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        String keyword = parser.parse(new StringDescriptionParser());
        TaskList matchingTasks = taskList.findTasks(keyword);
        ui.displayTasks(matchingTasks);
    }
}
