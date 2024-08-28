package NextGPT.command;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ui.showList(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
