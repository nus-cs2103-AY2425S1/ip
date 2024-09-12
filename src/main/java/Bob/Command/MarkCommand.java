package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

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

