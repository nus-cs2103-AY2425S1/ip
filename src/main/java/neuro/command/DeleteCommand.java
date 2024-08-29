package neuro.command;
import neuro.Ui;
import neuro.Storage;

import neuro.task.TaskList;

public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
