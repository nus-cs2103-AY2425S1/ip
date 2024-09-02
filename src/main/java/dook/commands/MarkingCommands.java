package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.Task;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command that marks and unmarks a task as done.
 */
public class MarkingCommands extends Command {

    private int taskNumber;
    private boolean isMarked;

    public MarkingCommands(int taskNumber, boolean isMarked) {
        this.taskNumber = taskNumber;
        this.isMarked = isMarked;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        Task task = tasks.getTask(taskNumber - 1);
        ui.separate();
        String message;
        if (this.isMarked) {
            task.markAsDone();
            message = "Nice! I've marked this task as done:";
            ui.showMessage(message);
        } else {
            task.unmark();
            message = "Ok, I've marked this task as not done yet:";
            ui.showMessage(message);
        }
        storage.write(tasks);
        message = message.concat("\n" + task.toString());
        ui.showMessage(task.toString());
        ui.separate();
        return message;
    }
}
