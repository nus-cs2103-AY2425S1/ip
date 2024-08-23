package matcha.command;
import matcha.Storage;
import matcha.TaskList;
import matcha.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setExit(true);
        ui.bye();
    }
}
