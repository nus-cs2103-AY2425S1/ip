package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        ui.printByeMessage();
        storage.writeFile(ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
