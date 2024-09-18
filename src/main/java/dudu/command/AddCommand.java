package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

public class AddCommand extends Command {
    private Task task;
    private boolean isUndoCommand;

    public AddCommand(Task task, boolean isUndoCommand) {
        this.task = task;
        this.isUndoCommand = isUndoCommand;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        int size = taskList.addTask(task);
        assert size > 0 : "Task not added to task list";
        if (!isUndoCommand) {
            Parser.pushToUndoStack(new DeleteCommand(size - 1, true));
        }
        storage.rewriteFile(taskList);
        return ui.addTask(task, size);
    }

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
