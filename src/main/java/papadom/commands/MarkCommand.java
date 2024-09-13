package papadom.commands;

import papadom.exceptions.NoTaskNumberException;
import papadom.exceptions.WrongTaskNumberException;
import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.utils.Ui;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WrongTaskNumberException, NoTaskNumberException {
        return ui.output(taskList.markTask(TEXT));
    }
}
