package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String message) {
        super(message);
        setIsExitCommand();
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.printGoodbyeMsg();
    }
}
