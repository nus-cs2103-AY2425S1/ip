public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException {
        ui.display(tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
