package lebron;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.showTaskDeleted(task);
        storage.saveTasks(taskList);
    }

    
    
}
