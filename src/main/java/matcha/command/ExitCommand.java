package matcha.command;

import matcha.storage.Storage;

import matcha.tasklist.TaskList;

import matcha.ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setExit(true);
        ui.bye();
    }
}
