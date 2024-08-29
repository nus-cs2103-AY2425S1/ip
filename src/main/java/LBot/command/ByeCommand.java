package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        ui.printBye();
        System.exit(0);
    }

    @Override
    public String toString() {
        return "bye command ";
    }
}
