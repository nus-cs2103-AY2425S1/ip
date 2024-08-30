package command;

import exception.*;
import task.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends UserCommand {
    @Override 
    public boolean continueRunning() {
        return false;
    }

    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException {
        ui.exit();
        return;
    }
}