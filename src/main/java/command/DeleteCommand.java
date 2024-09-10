package command;

import task.Task;
import task.TaskList;

/**
 * DeleteCommand class is used to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;
    private TaskList taskList;
    private Task deletedTask;
    /**
     * Constructor for DeleteCommand.
     * @param index The index of the taskList to be deleted, starting from 1.
     * @param taskList The task list that the task is to be deleted from.
     */
    public DeleteCommand(int index, TaskList taskList) {
        this.index = index - 1;
        this.taskList = taskList;
    }

    /**
     * Executes the delete command.
     */
    @Override
    public void execute() {
        deletedTask = taskList.remove(index - 1);
    }
    
    @Override
    public String toString() {
        return String.format("Noted. I've removed this task:\n"
                    + deletedTask + " Now you have %d tasks in the list.",
                    taskList.size());
    }
}
