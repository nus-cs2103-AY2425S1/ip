package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, it displays all tasks with their corresponding indices.
 */
public class ListCommand extends Command {

    public static final String LIST_COMMAND_MESSAGE = "Here are the tasks in your list:";

    /**
     * Constructs a ListCommand with the specified message.
     *
     * @param message The message associated with the command.
     */
    public ListCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by listing all tasks currently in the task list.
     * The tasks are displayed with their corresponding indices in the list.
     *
     * @param tasks The list of tasks to be displayed.
     * @param storage The storage system (not used in this command).
     * @param ui The user interface (not used in this command).
     * @throws DuckException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        System.out.println(LIST_COMMAND_MESSAGE);
        tasks.printTasks();
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the ListCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
