package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * Represents a command to unmark a specified task as not done.
 */
public class UnMarkCommand extends Command {
    /**
     * The index of the task to be unmarked.
     */
    private final int taskIndex;

    /**
     * Instantiates a new UnMarkCommand.
     *
     * @param taskIndex the index of the task to unmark
     */
    public UnMarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, marking the specified task as not done.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @return a message indicating the task has been unmarked
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskIndex < taskList.getTaskList().size() : "Index is out of range!";
        assert taskIndex >= 0 : "Index is out of range!";
        if (taskIndex >= taskList.getTaskList().size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException("Task index is out of bounds!");
        }
        taskList.getTask(taskIndex).unMark();
        storage.saveTasks(taskList.getTaskList());
        return ui.printUnMark(taskList.getTask(taskIndex));
    }
}
