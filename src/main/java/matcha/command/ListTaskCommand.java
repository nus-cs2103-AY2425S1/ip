package matcha.command;
import matcha.TaskList;
import matcha.Storage;
import matcha.Ui;

public class ListTaskCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
