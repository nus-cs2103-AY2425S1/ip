package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a command to delete a task from a task list.
 */
public class DeleteCommand extends Command {
    private int index;
    private boolean isUndoCommand;

    /**
     * Constructs a CommandDelete.
     *
     * @param index Index of the task to be deleted in the task list.
     * @param isUndoCommand True if command is from an undo command else false.
     */
    public DeleteCommand(int index, boolean isUndoCommand) {
        this.index = index;
        this.isUndoCommand = isUndoCommand;
    }

    /**
     * Executes the delete task command.
     * Deletes the task from the task list.
     * Rewrites the local file to reflect the changes.
     * Adds an AddCommand to the undo stack if this command is not from an undo command.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     * @return Message notifying user of deleting the task.
     * @throws IOException If there is an error during rewriting the local file in storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        if (!taskList.checkIndexIsValid(index)) {
            return taskList.getInvalidIndexMessage();
        }
        Task deletedTask = taskList.deleteTask(index);
        assert deletedTask != null : "No task is deleted as index is out of range";
        if (!isUndoCommand) {
            Parser.pushToUndoStack(new AddCommand(deletedTask, true));
        }
        storage.rewriteFile(taskList);
        return ui.getDeleteTaskMessage(deletedTask);
    }
}
