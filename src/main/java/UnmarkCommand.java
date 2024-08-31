public class UnmarkCommand extends Command{
    int index;

    public UnmarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.get(index).markUndone();
        ui.printSectionBreak();
        storage.saveFromTaskList(taskList);
    }

    @Override
    public void setData() {

    }
}
