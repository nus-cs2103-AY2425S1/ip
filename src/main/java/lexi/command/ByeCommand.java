package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        super.setExit();
        ui.showBye();
    }
}
