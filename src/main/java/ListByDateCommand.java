public class ListByDateCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.listTasksByDate());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
