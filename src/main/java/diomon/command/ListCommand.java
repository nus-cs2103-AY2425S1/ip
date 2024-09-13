package diomon.command;

import diomon.Storage;
import diomon.TaskList;
import diomon.ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setResponse("Here ya go\n" + tasks);
    };
}
