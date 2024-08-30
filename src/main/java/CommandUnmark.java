import java.io.IOException;

public class CommandUnmark extends Command {
    int index;

    public CommandUnmark(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        Task unmarkedTask = taskList.unmarkTask(this.index);
        storage.rewriteFile(taskList);
        ui.markTask(unmarkedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
