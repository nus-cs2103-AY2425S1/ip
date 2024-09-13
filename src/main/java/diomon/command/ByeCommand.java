package diomon.command;

import diomon.Storage;
import diomon.TaskList;
import diomon.ui.Ui;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setResponse("Zzzzz...");
        canExit = true;
    };
}
