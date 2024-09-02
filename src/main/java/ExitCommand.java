// ExitCommand.java
import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

