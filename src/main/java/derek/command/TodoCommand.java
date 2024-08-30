package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;


/**
 * The {@code TodoCommand} class adds a todo task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the todo task.
 */
public class TodoCommand extends TaskCommand {

    /**
     * Constructs a {@code TodoCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public TodoCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to add the specified todo task to the task list.
     *
     * @param task the task to be added
     * @param storage the storage object containing the task list
     * @param ui the UI object to interact with the user
     */
    public void execute(Task task, Storage storage, Ui ui) {
        TaskList taskList = storage.getTaskList();
        taskList.add(task);
        ui.addTask(task);

    }

}
