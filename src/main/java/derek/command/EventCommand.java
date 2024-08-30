package derek.command;

import derek.*;
import derek.task.Task;
import derek.task.TaskList;


/**
 * The {@code EventCommand} class adds an event task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the event task.
 */
public class EventCommand extends TaskCommand {

    /**
     * Constructs an {@code EventCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public EventCommand(String command) {
        super(command);
    }

    /**
     * Executes the command to add the specified event task to the task list.
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
