package monique.command;

import monique.exception.DeleteException;
import monique.storage.Storage;
import monique.task.Task;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public class DeleteCommand extends Command {

    private final int taskNum;

    public DeleteCommand(int taskNum){
        super();
        this.taskNum= taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeleteException {
        if (this.taskNum > tasks.getNumItems() -1 || this.taskNum < 0){
            throw new DeleteException();
        }
        Task deletedTask = tasks.getTask(this.taskNum);
        tasks.deleteTask(this.taskNum);
        ui.deleteMessage(deletedTask,tasks);
    }

    @Override
    public boolean isActive(){
        return true;
    }
}
