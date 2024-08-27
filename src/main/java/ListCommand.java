public class ListCommand extends Command{

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.produceList(tasks.produceList());
    }

    @Override
    protected boolean isExit() {
        return false;
    }
}
