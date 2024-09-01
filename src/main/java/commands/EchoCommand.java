package commands;

import skibidi.Ui;
import skibidi.Command;
import storage.TaskStorage;

public class EchoCommand extends Command {
    private final String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        ui.printMessage(message);
        return true;
    }
}