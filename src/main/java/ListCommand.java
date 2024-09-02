import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
