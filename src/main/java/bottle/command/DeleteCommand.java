package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Task;
import bottle.task.TaskList;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {
    /**
     * The Task index.
     */
    private final int taskIndex;

    /**
     * Instantiates a new Delete command.
     *
     * @param taskIndex the task index
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskIndex < taskList.getTaskList().size() : "Index is out of range!";
        assert taskIndex >= 0 : "Index is out of range!";
        Task task = taskList.removeTask(taskIndex);
        storage.saveTasks(taskList.getTaskList());
        return ui.printDeleteMsg(taskList.getTaskList(), task);
    }
}
