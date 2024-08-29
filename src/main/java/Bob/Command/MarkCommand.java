package Bob.Command;

import Bob.Exception.BobException;
import Bob.Storage.Storage;
import Bob.Ui.Ui;
import java.util.ArrayList;
import Bob.Tasks.Task;

public class MarkCommand extends Command {
    private final int taskIndexMark;

    public MarkCommand(int taskIndexMark) {
        this.taskIndexMark = taskIndexMark;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskIndexMark < tasks.size() && taskIndexMark >= 0) {
            tasks.get(taskIndexMark).mark();
            storage.save(tasks);
            ui.showTaskMarked(tasks.get(taskIndexMark));
        } else {
            throw new BobException("Invalid index :(");
        }
    }
}

