import java.io.IOException;

public class CommandMark extends Command {
    int index;

    public CommandMark(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException  {
        Task markedaTask = taskList.markTask(this.index);
        storage.rewriteFile(taskList);
        ui.markTask(markedaTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
