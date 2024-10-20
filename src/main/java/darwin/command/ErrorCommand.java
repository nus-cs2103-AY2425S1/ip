package darwin.command;

import darwin.exception.DarwinException;
import darwin.task.TaskManager;
import darwin.ui.Ui;

public class ErrorCommand extends Command {
    public static final String CMD_WORD = "";
    private DarwinException e;

    public ErrorCommand(DarwinException e) {
        this.e = e;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.send(e.getMessage());
    }
}
