public class ListCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        ui.showMessage(list.toUiFormat());
    }
}
