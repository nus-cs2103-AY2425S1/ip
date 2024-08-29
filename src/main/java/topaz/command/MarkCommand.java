package topaz.command;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.task.Task;
import topaz.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    private int index;
    public MarkCommand(String keyword, int index) {
        super(keyword);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (super.keyword.equals("mark")) {
                Task task = taskList.markAsDone(index);
                ui.showDoneTaskStatus(task);
            } else {
                Task task = taskList.markAsUndone(index);
                ui.showUndoneTaskStatus(task, taskList.getSize());
            }
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showMarkIOBError(index);
        } catch (IOException e) {
            ui.showSaveIOEException(e);
        }
    }
}
