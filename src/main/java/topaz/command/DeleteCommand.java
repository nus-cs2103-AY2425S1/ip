package topaz.command;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.task.Task;
import topaz.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String keyword, int index) {
        super(keyword);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.removeTask(index - 1);
            storage.save(tasks);
            ui.showDeleteTask(task, tasks.getSize());
        } catch (IOException e) {
            ui.showSaveIOEException(e);
        }
    }
}
