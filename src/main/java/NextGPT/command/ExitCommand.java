package NextGPT.command;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;


public class ExitCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
