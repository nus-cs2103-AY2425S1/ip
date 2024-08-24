package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.Storage;

public class CommandBye extends Command {

    public CommandBye(String[] inputs) {
    }

    @Override
    public void execute() {
        Storage.saveTasksToFile();
        Ui.printMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}