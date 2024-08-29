package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.Task;
import neuro.task.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0) {
            ui.showError("Missing or invalid index for 'mark' command! Add a valid " +
                    "index for a task to mark, like 'mark 2'.");
            return;
        }

        try {
            Task task = tasks.getTask(index - 1);
            task.markDone();

            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(task.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Index out of bounds! Try calling the command 'list' to " +
                    "verify the index of the desired task.");
        }
    }
}
