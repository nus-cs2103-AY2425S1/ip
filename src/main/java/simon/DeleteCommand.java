package simon;

public class DeleteCommand implements Command{
    int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.pop(index);
        ui.showTaskDeleted(task, taskList.size());
        storage.saveToFile(taskList.toArr());

    }
}
