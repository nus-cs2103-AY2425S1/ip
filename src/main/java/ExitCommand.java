public class ExitCommand extends Command{


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.farewell();
    }

    @Override
    public void setData() {
    }
}
