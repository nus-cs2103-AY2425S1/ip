package Revir.User.Command;

import java.io.IOException;

import Revir.Tasks.TaskList;
import Revir.User.Ui;

public class Delete extends Command{
    private int taskIndex;

    public Delete(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws IOException {
        ui.showResult(taskList.remove(this.taskIndex));
    }
}
