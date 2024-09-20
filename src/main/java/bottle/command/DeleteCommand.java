package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Task;
import bottle.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted.
     */
    private final int taskIndex;

    /**
     * Instantiates a new DeleteCommand with the specified task index.
     *
     * @param taskIndex the index of the task to delete
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskIndex < taskList.getTaskList().size() : "Index is out of range!";
        assert taskIndex >= 0 : "Index is out of range!";
        if (taskIndex >= taskList.getTaskList().size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException("Task index is out of bounds!");
        }
        Task task = taskList.removeTask(taskIndex);
        storage.saveTasks(taskList.getTaskList());
        return ui.printDeleteMsg(taskList.getTaskList(), task);
    }
}
