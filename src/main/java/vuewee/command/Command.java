package vuewee.command;

import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;

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
    public abstract void execute(TaskListUI ui, TaskList taskList, CommandParser parser);
}
