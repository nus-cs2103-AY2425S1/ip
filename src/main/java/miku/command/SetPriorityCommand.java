package miku.command;

import miku.utility.Priority;
import miku.utility.Response;
import miku.utility.Storage;
import miku.utility.TaskList;

/**
 * Represents the command to change a task's priority
 */
public class SetPriorityCommand extends Command {
    private final int index;
    private final Priority newPriority;
    /**
     * Instantiates a SetPriorityCommand.
     */
    public SetPriorityCommand(int numOfItem, Priority newPriority) {
        this.index = numOfItem - 1;
        this.newPriority = newPriority;
    }
    @Override
    public void execute(TaskList taskList, Response ui, Storage storage) {
        taskList.get(index).setPriority(newPriority);
        storage.saveFromTaskList(taskList);
        ui.setResponse(ui.setPrioritySuccessful(index, newPriority));
    }
}
