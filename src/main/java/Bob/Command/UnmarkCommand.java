package Bob.Command;

import Bob.Exception.BobException;
import java.util.ArrayList;
import Bob.Tasks.Task;
import Bob.Storage.Storage;
import Bob.Ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndexUnmark;

    public UnmarkCommand(int taskIndexUnmark) {
        this.taskIndexUnmark = taskIndexUnmark;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskIndexUnmark < tasks.size() && taskIndexUnmark >= 0) {
            tasks.get(taskIndexUnmark).unmark();
            storage.save(tasks);
            ui.showTaskUnmarked(tasks.get(taskIndexUnmark));
        } else {
            throw new BobException("Invalid index :(");
        }
    }
}
