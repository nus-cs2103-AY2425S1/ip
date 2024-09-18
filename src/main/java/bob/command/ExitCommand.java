package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String argument) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ui.printExit();
    }
}
