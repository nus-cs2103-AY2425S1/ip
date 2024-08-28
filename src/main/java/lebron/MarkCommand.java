package lebron;

public class MarkCommand extends Command {
    
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.markTask(index);
        ui.showTaskMarked(task);
        storage.saveTasks(taskList);
    }

    
}
