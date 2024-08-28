package stan.commands;
import stan.TaskList;
import stan.Ui;
import stan.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
