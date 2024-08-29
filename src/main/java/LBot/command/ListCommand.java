package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        ui.printTaskList(tasks);
    }

    @Override
    public String toString() {
        return "list command ";
    }
}
