package bob.Command;

import bob.Exception.BobException;
import bob.Storage.Storage;
import bob.Tasks.Task;
import bob.Ui.Ui;

import java.util.ArrayList;


public class MarkCommand extends Command {
    private final int taskIndexMark;

    public MarkCommand(int taskIndexMark) {

        this.taskIndexMark = taskIndexMark;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskIndexMark < tasks.size() && taskIndexMark >= 0) {
            tasks.get(taskIndexMark).mark();
            storage.save(tasks);
            return ui.showTaskMarked(tasks.get(taskIndexMark));
        } else {
            throw new BobException("Invalid index :(");
        }
    }
}

