package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import java.io.IOException;

/**
 * Represents a command to exit the application.
 *
 * When executed, it sends goodbye to the user and this is a terminal command.
 *
 * @author Choi Yi Hao
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.completeModification();
        String output = "Bye. Hope to see you again!";
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
