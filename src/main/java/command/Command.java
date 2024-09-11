package command;

import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to be executed.
 */
abstract public class Command {
    protected boolean isExit = false;

    /**
     * Executes the command.
     *
     * @param tasks           the list of tasks
     * @param ui              the user interface
     * @param storage         the storage
     * @param taskListHistory
     * @throws LightException if an error occurs during execution
     */
    abstract public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command
     */
    public boolean isExit() {

        return isExit;
    }

    public enum CommandType {
        BYE,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        EXIT,
        MARK,
        UNMARK,
        HELP,
        UNDO,
        REDO
    }
}
