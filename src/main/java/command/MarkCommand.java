package command;

import exception.ExecuteCommandException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class MarkCommand extends Command {
    private int taskID;

    public MarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        tasks.markTaskAsComplete(taskID);
        // TODO: RETURN SMT??
    }

    @Override
    public String toString() {
        return "mark command " + taskID;
    }
}
