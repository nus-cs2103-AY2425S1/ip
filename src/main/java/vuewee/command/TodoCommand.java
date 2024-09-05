package vuewee.command;

import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.task.TodoTask;

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
    public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
        parser.parse(true);
        ui.addTask(new TodoTask(parser.getDescription()));
    }
}
