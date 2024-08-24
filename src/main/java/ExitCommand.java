import java.io.IOException;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DookException,IOException {
        storage.write(taskList);
        ui.exit();
        super.isExit = true;
    }
}
