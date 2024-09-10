package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.Task;
import pixel.task.TaskList;

/**
 * Represents a command to mark a task as done in the task list, or mark a task
 * as not done in the task list.
 */
public class MarkCommand extends Command {
    private int taskListIndex;

    /**
     * Constructs a MarkCommand object with the specified task list index.
     *
     * @param taskListIndex The index of the task in the task list to be marked as
     *                      done.
     */
    public MarkCommand(int taskListIndex) {
        super(false);
        this.taskListIndex = taskListIndex;
    }

    /**
     * Constructs a MarkCommand object with the specified input.
     *
     * @param input The input string representing the task list index to be marked
     *              as done.
     * @throws PixelException If the input is not a valid number.
     */
    public MarkCommand(String input) throws PixelException {
        super(false);
        if (!input.matches("-?(0|[1-9]\\d*)")) {
            throw new PixelException(String.format("%s need to be a number", input));
        }
        this.taskListIndex = Integer.parseInt(input) - 1;
    }

    /**
     * Executes the mark command by marking the specified task as either done or not
     * done in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving the task list.
     * @throws PixelException If the task list index is out of range.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        if (this.taskListIndex < 0 || this.taskListIndex >= taskList.size()) {
            throw new PixelException(String.format(
                    "%d is out of range of task list, index should be range between %d and %d inclusive",
                    this.taskListIndex + 1, 1, taskList.size()));
        }
        assert this.taskListIndex >= 0 && this.taskListIndex < taskList.size()
                : "Task list index should within index of task list";
        Task task = taskList.getTaskAtIndex(this.taskListIndex);
        boolean isDone = task.toggleIsDone();
        if (isDone) {
            ui.pixelSays("Nice! I've marked this task as done:", " " + task);
        } else {
            ui.pixelSays("OK, I've marked this task as not done yet:", " " + task);
        }
    }

    /**
     * Executes the mark command and returns the response message.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving the task list.
     * @return The response message.
     * @throws PixelException If the task list index is out of range.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        if (this.taskListIndex < 0 || this.taskListIndex >= taskList.size()) {
            throw new PixelException(String.format(
                    "%d is out of range of task list, index should be range between %d and %d inclusive",
                    this.taskListIndex + 1, 1, taskList.size()));
        }
        Task task = taskList.getTaskAtIndex(this.taskListIndex);
        boolean isDone = task.toggleIsDone();
        if (isDone) {
            return String.format("Nice! I've marked this task as done:\n %s", task);
        } else {
            return String.format("OK, I've marked this task as not done yet:\n %s", task);
        }
    }
}
