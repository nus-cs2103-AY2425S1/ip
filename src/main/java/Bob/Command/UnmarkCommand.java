package bob.Command;

import bob.Exception.BobException;
import bob.Storage.Storage;
import bob.Ui.Ui;
import bob.Tasks.Task;
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
