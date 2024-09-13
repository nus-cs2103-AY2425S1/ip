package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.tasks.Task;
import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int taskIndexUnmark;

    public UnmarkCommand(int taskIndexUnmark) {
        this.taskIndexUnmark = taskIndexUnmark;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskIndexUnmark < tasks.size() && taskIndexUnmark >= 0) {
            tasks.get(taskIndexUnmark).unmark();
            storage.save(tasks);
            return ui.showTaskUnmarked(tasks.get(taskIndexUnmark));
        } else {
            throw new BobException("Invalid index :(");
        }
    }
}
