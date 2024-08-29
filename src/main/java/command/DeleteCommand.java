package command;

import exception.ExecuteCommandException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class DeleteCommand extends Command {
    private int taskID;

    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        tasks.deleteTask(taskID);
        // TODO: RETURN SMT??
    }

    @Override
    public String toString() {
        return "del command " + taskID;
    }
}
