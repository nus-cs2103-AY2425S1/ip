package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.Task;
import pixel.task.TaskList;

/**
 * Represents a command to delete a task from the list. When executed, it
 * deletes the task from the list.
 */
public class DeleteCommand extends Command {
    private int taskListIndex;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param input the input string representing the task list index to be deleted
     * @throws PixelException if the input is not a valid number
     */
    public DeleteCommand(String input) throws PixelException {
        super(false);
        if (!input.matches("-?(0|[1-9]\\d*)")) {
            throw new PixelException(String.format("%s need to be a number", input));
        }
        this.taskListIndex = Integer.parseInt(input) - 1;
    }

    /**
     * Executes the delete command, deleting a task from the list.
     *
     * @param taskList the task list
     * @param ui       the user interface
     * @param storage  the storage
     * @throws PixelException if the task list index is out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        if (this.taskListIndex < 0 || this.taskListIndex >= taskList.size()) {
            throw new PixelException(String.format(
                    "%d is out of range of task list, index should be range between %d and %d inclusive",
                    this.taskListIndex + 1, 1, taskList.size()));
        }
        Task task = taskList.getTaskAtIndex(taskListIndex);
        taskList.deleteTask(task);
        ui.pixelSays("Noted. I've removed this task:", "  " + task, "Now you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * Executes the delete command and returns the response message.
     *
     * @param taskList the task list
     * @param ui       the user interface
     * @param storage  the storage
     * @return the response message
     * @throws PixelException if the task list index is out of range
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        if (this.taskListIndex < 0 || this.taskListIndex >= taskList.size()) {
            throw new PixelException(String.format(
                    "%d is out of range of task list, index should be range between %d and %d inclusive",
                    this.taskListIndex + 1, 1, taskList.size()));
        }
        Task task = taskList.getTaskAtIndex(taskListIndex);
        taskList.deleteTask(task);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", task,
                taskList.size());
    }
}
