package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.parser.description.StringDescriptionParser;
import vuewee.task.TaskList;
import vuewee.task.TodoTask;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to add a todo task.
 */
class TodoCommand extends Command {
    /**
     * Executes the todo command by parsing the input, creating a new todo task, and
     * adding it to the task list.
     *
     * @param ui       the user interface for displaying messages
     * @param taskList the task list to add the todo task to
     * @param parser   the command parser for parsing the input
     */
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        String desc = parser.<String>parse(new StringDescriptionParser());
        ui.addTask(new TodoTask(desc));
    }
}
