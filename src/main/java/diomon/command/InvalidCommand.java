package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;
import diomon.ui.Ui;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setResponse("Nein, this command dont exist");
    }
}
