package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * The type Un mark command.
 */
public class UnMarkCommand extends Command {
    /**
     * The Task index.
     */
    private final int taskIndex;

    /**
     * Instantiates a new Un mark command.
     *
     * @param taskIndex the task index
     */
    public UnMarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskIndex < taskList.getTaskList().size() : "Index is out of range!";
        assert taskIndex >= 0 : "Index is out of range!";
        taskList.getTask(taskIndex).unMark();
        storage.saveTasks(taskList.getTaskList());
        return ui.printUnMark(taskList.getTask(taskIndex));
    }
}
