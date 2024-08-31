public class MarkCommand extends Command{
    int index;

    public MarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.get(index).markDone();
        storage.saveFromTaskList(taskList);
        ui.printSectionBreak();
    }

    @Override
    public void setData() {

    }
}
