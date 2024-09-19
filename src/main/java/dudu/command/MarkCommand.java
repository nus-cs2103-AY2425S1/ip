package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isUndoCommand;

    /**
     * Constructs a CommandMark with the specified task.
     *
     * @param index Index of the task to be marked in the task list.
     * @param isUndoCommand True if command is from an undo command else false.
     */
    public MarkCommand(int index, boolean isUndoCommand) {
        this.index = index;
        this.isUndoCommand = isUndoCommand;
    }

    /**
     * Executes the mark task command.
     * Marks the task in the task list as completed.
     * Rewrites the local file to reflect the changes.
     * Adds an UnmarkCommand to the undo stack if this command is not from an undo command.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     * @return Message notifying user of marking the task.
     * @throws IOException If there is an error during rewriting the local file in storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        if (!taskList.checkIndexIsValid(index)) {
            return taskList.getInvalidIndexMessage();
        }
        Task markedTask = taskList.markTask(this.index);
        assert markedTask != null : "Task was not marked successfully";
        if (!isUndoCommand) {
            Parser.pushToUndoStack(new UnmarkCommand(index, true));
        }
        storage.rewriteFile(taskList);
        return ui.getMarkTaskMessage(markedTask);
    }
}
