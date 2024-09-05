import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }
}
