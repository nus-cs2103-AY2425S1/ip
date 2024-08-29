package neuro.command;

import neuro.Ui;
import neuro.Storage;

import neuro.task.Task;
import neuro.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0) {
            ui.showError("Missing or invalid index for 'delete' command! Add a valid " +
                    "index for a task to delete, like 'delete 2'.");
            return;
        }

        try {
            Task task = tasks.removeTask(index - 1);
            ui.showMessage("Ok, I've removed this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Index out of bounds! Try calling the command 'list' to " +
                    "verify the index of the desired task.");
        }
    }
}
