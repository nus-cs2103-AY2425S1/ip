package revir.user.command;

import java.io.IOException;

import revir.tasks.TaskList;
import revir.user.Ui;

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
