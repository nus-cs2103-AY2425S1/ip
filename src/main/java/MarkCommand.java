public class MarkCommand implements Command{
    int index;
    boolean mark;
    public MarkCommand(boolean mark, int index) {

        this.index = index;
        this.mark = mark;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(mark, index);
        if(mark) {
            ui.showTaskMarked(taskList.get(index));
        }
        else{
            ui.showTaskUnmarked(taskList.get(index));
        }
        storage.saveToFile(taskList.toArr());

    }
}
