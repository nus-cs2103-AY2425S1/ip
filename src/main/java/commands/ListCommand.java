package commands;

import exceptions.InvalidCommandException;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        taskList.listAll();
    }
}
