package monique.command;

import monique.exception.MarkException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum){
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MarkException {
        if (this.taskNum > tasks.getNumItems()-1 || this.taskNum < 0) {
            throw new MarkException();
        }
        tasks.markTask(this.taskNum);
        ui.markMessage(tasks.getTask(this.taskNum));
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
