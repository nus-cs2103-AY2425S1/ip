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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(taskIndex).mark();
        ui.printMark(taskList.getTask(taskIndex));
        storage.saveTasks(taskList.getTaskList());
    }
}
