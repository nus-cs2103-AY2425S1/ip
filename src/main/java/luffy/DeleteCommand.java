package luffy;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        Task deletedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.showDeletedTask(deletedTask, taskList);
    }

}
