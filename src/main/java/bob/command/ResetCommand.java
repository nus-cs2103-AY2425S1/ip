package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

import java.util.Map;

public class ResetCommand extends Command {
    public static final String COMMAND = "reset";

    public ResetCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.reset();
        ui.printWithFormat("OK, I've removed all your tasks.");
    }
}
