package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a command to add a task to a task list.
 */
public class AddCommand extends Command {
    private Task task;
    private boolean isUndoCommand;

    /**
     * Constructs an AddCommand.
     *
     * @param task Task to be added.
     * @param isUndoCommand True if command is from an undo command else false.
     */
    public AddCommand(Task task, boolean isUndoCommand) {
        this.task = task;
        this.isUndoCommand = isUndoCommand;
    }

    /**
     * Executes the add task command.
     * Adds the task to the task list.
     * Rewrites the local file to reflect the changes.
     * Adds a DeleteCommand to the undo stack if this command is not from an undo command.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     * @return Message notifying user of adding the task.
     * @throws IOException If there is an error during rewriting the local file in storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        int size = taskList.addTask(task);
        assert size > 0 : "Task not added to task list";
        if (!isUndoCommand) {
            Parser.pushToUndoStack(new DeleteCommand(size - 1, true));
        }
        storage.rewriteFile(taskList);
        return ui.getAddTaskMessage(task, size);
    }

    /**
     * Compares this object with another object.
     * Compares for same stored task and undo command status.
     *
     * @param object Object to compare with.
     * @return True if object has the same fields else false.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof AddCommand)) {
            return false;
        }
        AddCommand otherAddCommand = (AddCommand) object;
        boolean hasSameIsUndoCommand = this.isUndoCommand == otherAddCommand.isUndoCommand;
        boolean hasSameTask = this.task.equals(otherAddCommand.task);
        return hasSameIsUndoCommand && hasSameTask;
    }
}
