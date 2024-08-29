package derek.command;

import derek.Storage;
import derek.Ui;
import derek.command.Command;

public class LeavingCommand extends Command {

    public LeavingCommand(String command) {
        super(command);
    }

    public void execute(Storage storage, Ui ui) {
        storage.storeInFile();
        ui.printLeavingMessage();
    }
}
