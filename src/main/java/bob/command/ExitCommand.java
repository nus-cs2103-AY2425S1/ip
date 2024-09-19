package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

import java.io.IOException;
import java.util.Map;

public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    public ExitCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ui.printExit();
    }
}
