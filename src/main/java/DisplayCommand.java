public class DisplayCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
