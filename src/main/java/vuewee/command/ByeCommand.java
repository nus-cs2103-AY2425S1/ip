package vuewee.command;

import vuewee.EndProgramException;
import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;

/**
 * Represents a command to exit the program.
 */
class ByeCommand extends Command {
    /**
     * Executes the ByeCommand.
     *
     * @param ui       the user interface for displaying messages
     * @param taskList the task list to perform operations on
     * @param parser   the command parser for parsing user input
     */
    public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
        throw new EndProgramException();
    }
}
