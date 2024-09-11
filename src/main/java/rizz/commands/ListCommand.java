package rizz.commands;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;


public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.outputList(tasks);
    }
}
