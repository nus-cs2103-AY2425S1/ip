package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code DeleteCommand} class removes a task from the task list.
 * It extends the {@code Command} class and executes the command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a {@code DeleteCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to delete the task at the specified index.
     *
     * @param index the index of the task to be deleted
     * @param storage the storage object containing the task list
     * @param ui the UI object to interact with the user
     */
    public void execute(int index, Storage storage, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        ui.removeTask(task);
    }
}
