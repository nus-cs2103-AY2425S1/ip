package lebron;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.markTask(index);
        String msg = ui.showTaskMarked(task);
        storage.saveTasks(taskList);
        return msg;
    }

}