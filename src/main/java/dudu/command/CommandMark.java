package dudu.command;

import dudu.command.Command;
import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

import java.io.IOException;

public class CommandMark extends Command {
    int index;

    /**
     * Constructs a CommandMark with the specified task.
     *
     * @param index The index of the task to be marked.
     */
    public CommandMark(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task in the task list, updating
     * the user interface with the deleted task, and saving the updated task list to storage.
     *
     * @param taskList The task list on which the command is executed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the task.
     * @throws IOException If there is an error during saving the task to storage.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException  {
        Task markedTask = taskList.markTask(this.index);
        storage.rewriteFile(taskList);
        ui.markTask(markedTask);
    }

    /**
     * Indicates that this command will not cause the application to exit.
     *
     * @return false, as this command always does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
