public class ShowListCommand extends Command {
    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        ui.showTaskLists(taskLists);
    }
    @Override
    public boolean isBye() {
        return false;
    }
}

