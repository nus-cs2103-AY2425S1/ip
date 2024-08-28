import java.io.IOException;

public class ExitCommand implements Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayBye();
        return false;
    }
}
