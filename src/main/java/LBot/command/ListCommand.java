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
        // print task list idk how
        // TODO: use ui to print??
        ui.print(tasks.toString());
    }

    @Override
    public String toString() {
        return "list command ";
    }
}
