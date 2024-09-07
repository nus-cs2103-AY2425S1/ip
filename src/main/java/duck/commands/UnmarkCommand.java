package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;
import duck.util.Utils;

/**
 * Represents a command to unmark a task as incomplete.
 * This command is used to indicate that a task is no longer completed and should be marked as incomplete.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand with the specified message.
     *
     * @param message The message associated with the command.
     */
    public UnmarkCommand(String message) {
        super(message);
    }

    /**
     * Executes the unmark command, marking the specified task as incomplete.
     * It verifies the correctness of the command format and updates the task list and storage accordingly.
     *
     * @param tasks   The list of tasks to be manipulated by the command.
     * @param storage The storage system for saving and loading tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @throws DuckException If an error occurs during the execution of the command,
     *                       such as an invalid task index or incorrect command format.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        if (!Utils.isCorrectUpdateFormat(message)) {
            throw new DuckException("Update tasks with correct format please >:(\n"
                    + "mark/unmark {index of task to update}\n");
        }

        String[] words = message.split(" ");

        try {
            tasks.get(Integer.parseInt(words[1]) - 1).markAsIncomplete();
            storage.writeTasks(tasks);
        } catch (NumberFormatException e) {
            throw new DuckException("Oops! you have to indicate a valid task index!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Oops! Index out of bound :( Input a valid task index.\n");
        }
    }

    /**
     * Indicates whether this command signifies an exit operation.
     *
     * @return false, as unmarking a task does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
