package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).unmark();
            ui.showUnmark(tasks.getTask(idx - 1));
        } catch (IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! Task index is out of bounds.");
        }

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            ui.showError("We cannot save the tasks: " + e.getMessage());
        }
    }
}
