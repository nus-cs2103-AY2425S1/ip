package pixel.command;

import pixel.Storage;
import pixel.PixelException;
import pixel.Ui;
import pixel.task.Task;
import pixel.task.TaskList;

public class MarkCommand extends Command {
    int taskListIndex;

    public MarkCommand(int taskListIndex) {
        super(false);
        this.taskListIndex = taskListIndex;
    }

    public MarkCommand(String input) throws PixelException {
        super(false);
        if (!input.matches("-?(0|[1-9]\\d*)")) {
            throw new PixelException(String.format("%s need to be a number", input));
        }
        this.taskListIndex = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        if (this.taskListIndex < 0 || this.taskListIndex >= taskList.size()) {
            throw new PixelException(String
                    .format("%d is out of range of task list, index should be range between %d and %d inclusive",
                            this.taskListIndex + 1, 1, taskList.size()));
        }
        Task task = taskList.getTaskAtIndex(this.taskListIndex);
        boolean isDone = task.toggleIsDone();
        if (isDone) {
            ui.PixelSays("Nice! I've marked this task as done:",
                    " " + task);
        } else {
            ui.PixelSays("OK, I've marked this task as not done yet:",
                    " " + task);
        }
    }
}