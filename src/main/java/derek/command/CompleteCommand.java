package derek.command;

import derek.Storage;
import derek.task.Task;
import derek.task.TaskList;
import derek.Ui;

/**
 * The {@code CompleteCommand} class marks a task as completed in the task list.
 * It extends the {@code Command} class and executes the command to complete a task.
 */
public class CompleteCommand extends Command {

    /**
     * Constructs a {@code CompleteCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public CompleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to mark the task at the specified index as completed.
     *
     * @param storage the storage object containing the task list
     * @param index the index of the task to be marked as completed
     * @param ui the UI object to interact with the user
     */
    public void execute(Storage storage, int index, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        task.markCompleted();
        ui.completeTask(task);
    }
}
