public class ListCommand extends Command{
    public ListCommand(String command) {
        super(command);
    }
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        ui.showList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
