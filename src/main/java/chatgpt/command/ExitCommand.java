package chatgpt.command;

import chatgpt.task.TaskList;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
