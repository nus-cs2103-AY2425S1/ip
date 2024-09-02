package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

public abstract class Command {
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws GalliumException {

    }

    public boolean isExit() {
        return false;
    }
}
