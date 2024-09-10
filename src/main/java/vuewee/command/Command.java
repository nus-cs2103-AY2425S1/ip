package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.ui.TaskListUi;

/**
 * The Command class is an abstract class that represents a Vuewee command.
 * Subclasses of Command must implement the execute method, which defines the
 * behavior of the command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param ui       The user interface for displaying messages to the user.
     * @param taskList The task list that the command operates on.
     * @param parser   The command parser for parsing user input.
     */
    public abstract void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser);
}
