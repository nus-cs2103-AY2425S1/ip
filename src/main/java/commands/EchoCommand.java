package commands;

import common.Ui;
import common.Command;
import storage.TaskStorage;

public class EchoCommand extends Command {
    private String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        ui.printMessage(message);
        return true;
    }
}