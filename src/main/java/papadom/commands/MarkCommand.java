package papadom.commands;

import papadom.Exceptions.NoTaskNumberException;
import papadom.Exceptions.WrongTaskNumberException;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;
/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final String TEXT;
    /**
     * Constructs a MarkCommand with the specified text input.
     *
     * @param text The input string that specifies which task to mark.
     */
    public MarkCommand(String text) {
        this.TEXT = text;
    }

    /**
     * Executes the command to mark a task as completed.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface for outputting messages.
     * @param storage The storage system for saving the task list.
     * @throws WrongTaskNumberException If the task number is invalid.
     * @throws NoTaskNumberException If no task number is provided.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WrongTaskNumberException, NoTaskNumberException {
        ui.output(taskList.markTask(TEXT));
    }
}
