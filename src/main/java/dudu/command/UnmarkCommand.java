package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a command to mark a task as uncompleted.
 */
public class UnmarkCommand extends Command {
    private int index;
    private boolean isUndoCommand;

    /**
     * Constructs a CommandMark with the specified task.
     *
     * @param index Index of the task to be unmarked in the task list.
     * @param isUndoCommand True if command is from an undo command else false.
     */
    public UnmarkCommand(int index, boolean isUndoCommand) {
        this.index = index;
        this.isUndoCommand = isUndoCommand;
    }

    /**
     * Executes the unmark task command.
     * Marks the task in the task list as uncompleted.
     * Rewrites the local file to reflect the changes.
     * Adds an MarkCommand to the undo stack if this command is not from an undo command.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     * @return Message notifying user of unmarking the task.
     * @throws IOException If there is an error during rewriting the local file in storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        if (!isUndoCommand) {
            Parser.pushToUndoStack(new MarkCommand(index, true));
        }
        Task unmarkedTask = taskList.unmarkTask(this.index);
        assert unmarkedTask != null : "Task was not unmarked successfully";
        storage.rewriteFile(taskList);
        return ui.getUnmarkTaskMessage(unmarkedTask);
    }
}
