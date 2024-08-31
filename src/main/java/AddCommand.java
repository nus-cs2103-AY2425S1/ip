public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.addItem(task);
        storage.saveFromTaskList(taskList);
        ui.printSectionBreak();
    }

    @Override
    public void setData() {

    }
}
