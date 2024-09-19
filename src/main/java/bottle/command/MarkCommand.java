package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * Represents a command to mark a specified task as done.
 */
public class MarkCommand extends Command {
    /**
     * The index of the task to be marked.
     */
    private final int taskIndex;

    /**
     * Instantiates a new MarkCommand.
     *
     * @param taskIndex the index of the task to mark
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking the specified task as done.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @return a message indicating the task has been marked
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskIndex < taskList.getTaskList().size() : "Index is out of range!";
        assert taskIndex >= 0 : "Index is out of range!";
        if (taskIndex >= taskList.getTaskList().size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException("Task index is out of bounds!");
        }
        taskList.getTask(taskIndex).mark();
        storage.saveTasks(taskList.getTaskList());
        return ui.printMark(taskList.getTask(taskIndex));
    }
}
