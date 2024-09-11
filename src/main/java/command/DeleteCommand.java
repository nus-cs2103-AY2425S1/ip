package command;

import main.Storage;
import main.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final String input;

    /**
     * Constructs a DeleteCommand instance with the specified input.
     *
     * @param input The input string containing the task number to delete.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the delete command by parsing the task number from the input and
     * deleting the corresponding task from the task list.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param storage The storage used to persist the changes.
     * @throws NumberFormatException if the task number is not a valid integer.
     * @throws ArrayIndexOutOfBoundsException if the input does not contain a task number.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String[] parts = input.split(" ", 2);
        int deleteNum = Integer.parseInt(parts[1]);
        return storage.deleteTask(deleteNum, tasks);
    }
}
