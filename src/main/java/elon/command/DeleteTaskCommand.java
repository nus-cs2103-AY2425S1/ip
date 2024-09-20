package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

import java.io.IOException;

public class DeleteTaskCommand extends Command{
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        Task removedTask = list.removeTask(index);
        storage.saveFile(list);
        return ui.deleteTask(removedTask, list);
    }
}
