package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a delete task user command into the chatbot
 */
public class DeleteCommand extends Command {
    private int index;
    private boolean isUndoCommand;

    /**
     * Constructs a CommandDelete with the specified index
     *
     * @param index The index of the task to be deleted
     * @param isUndoCommand Status if command is from an undo command
     */
    public DeleteCommand(int index, boolean isUndoCommand) {
        this.index = index;
        this.isUndoCommand = isUndoCommand;
    }

    /**
     * Executes the command by deleting the task from the task list, updating
     * the user interface with the deleted task, and saving the updated task list to storage
     *
     * @param taskList The task list on which the command is executed
     * @param ui The user interface to interact with the user
     * @param storage The storage to save the task
     * @return Successful delete task response
     * @throws IOException If there is an error during saving the task to storage
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        Task deletedTask = taskList.deleteTask(index);
        assert deletedTask != null : "No task is deleted as index is out of range";
        if (!isUndoCommand) {
            Parser.pushToUndoStack(new AddCommand(deletedTask, true));
        }
        storage.rewriteFile(taskList);
        return ui.getDeleteTaskMessage(deletedTask);
    }
}
