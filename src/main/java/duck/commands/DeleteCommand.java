package duck.commands;

import duck.common.Message;
import duck.common.Utils;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;


/**
 * Represents a command to delete a task from the task list.
 * When executed, this command checks the format of the delete message, parses the task index,
 * and deletes the specified task from the task list.
 */
public class DeleteCommand extends Command {


    private static final String DELETE_COMMAND_ERROR_MESSAGE = """
            Delete tasks with correct format please >:(
            delete {index of task to delete}
            """;

    /**
     * Constructs a DeleteCommand with the specified message.
     *
     * @param message The message containing the command and its arguments.
     */
    public DeleteCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by deleting the specified task from the task list.
     * If the message is in the wrong format or if the index is invalid, it throws a DuckException.
     *
     * @param tasks The list of tasks from which the task will be deleted.
     * @param storage The storage system for saving and loading tasks.
     * @param ui The user interface for displaying messages to the user.
     * @throws DuckException If there is an error in the format of the delete command, the task index is
     *         not a number, or the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        if (!Utils.isCorrectUpdateFormat(message)) {
            throw new DuckException(DELETE_COMMAND_ERROR_MESSAGE);
        }

        int taskIndex = getTaskIndex(message);
        tasks.deleteTask(taskIndex, storage);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as this command does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private int getTaskIndex(String message) throws DuckException {
        String[] words = message.split(" ");
        try {
            return Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DuckException(Message.INVALID_INDEX_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException(Message.INDEX_OUT_OF_BOUNDS);
        }
    }
}
