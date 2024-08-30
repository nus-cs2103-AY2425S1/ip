import java.io.IOException;

public class CommandDelete extends Command {
    int index;

    public CommandDelete(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        Task deletedTask = taskList.deleteTask(this.index);
        storage.rewriteFile(taskList);
        ui.markTask(deletedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
