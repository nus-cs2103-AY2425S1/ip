package derek.command;

import derek.Storage;
import derek.task.Task;
import derek.task.TaskList;
import derek.Ui;


/**
 * The {@code IncompleteCommand} class marks a task as incomplete in the task list.
 * It extends the {@code Command} class and executes the command to mark a task as incomplete.
 */
public class IncompleteCommand extends Command {

    /**
     * Constructs an {@code IncompleteCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public IncompleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to mark the task at the specified index as incomplete.
     *
     * @param storage the storage object containing the task list
     * @param index the index of the task to be marked as incomplete
     * @param ui the UI object to interact with the user
     */
    public void execute(Storage storage, int index, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        task.markIncomplete();
        ui.incompleteTask(task);
    }
}
