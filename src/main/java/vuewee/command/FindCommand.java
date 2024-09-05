package vuewee.command;

import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;

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
    public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
        parser.parse(true);
        String keyword = parser.getDescription();
        TaskList matchingTasks = taskList.findTasks(keyword);
        ui.displayTasks(matchingTasks);
    }
}
