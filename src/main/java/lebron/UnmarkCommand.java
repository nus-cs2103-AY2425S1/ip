package lebron;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.unmarkTask(index);
        String msg = ui.showTaskUnmarked(task);
        storage.saveTasks(taskList);
        return msg;
    }

}
