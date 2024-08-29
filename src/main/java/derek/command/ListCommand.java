package derek.command;

import derek.Storage;
import derek.Ui;
import derek.command.Command;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }
    public void execute(Storage storage, Ui ui) {
        ui.returnList();
    }
}
