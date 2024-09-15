package astor.command;

import java.io.IOException;

import astor.Storage;
import astor.TaskList;
import astor.Ui;

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
        ui.showBye();
        String byeStatement = "Bye. Hope to see you again!";
        return byeStatement;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
