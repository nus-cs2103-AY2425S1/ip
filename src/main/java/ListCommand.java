public class ListCommand extends Command{
    public ListCommand() {

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LightException {
        ui.showMessage(TaskList.arrayToNumberedString(tasks));
    }
}
