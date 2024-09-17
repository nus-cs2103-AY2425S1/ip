package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        System.exit(0);
        return ui.goodbye();
    }
}
