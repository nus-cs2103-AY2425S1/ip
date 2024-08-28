import java.io.IOException;

public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.listAllTasks(ui);
        return true;
    }
}
