package command;

import exception.ExecuteCommandException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class ByeCommand extends Command{
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        // DO WHAT LMAO
        // say bye bye
        System.exit(0);
    }

    @Override
    public String toString() {
        return "bye command ";
    }
}
