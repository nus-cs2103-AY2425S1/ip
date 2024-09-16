package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {
    /**
     * The Task index.
     */
    private final int taskIndex;

    /**
     * Instantiates a new Mark command.
     *
     * @param taskIndex the task index
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskIndex < taskList.getTaskList().size() : "Index is out of range!";
        assert taskIndex >= 0 : "Index is out of range!";
        taskList.getTask(taskIndex).mark();
        storage.saveTasks(taskList.getTaskList());
        return ui.printMark(taskList.getTask(taskIndex));


    }
}
