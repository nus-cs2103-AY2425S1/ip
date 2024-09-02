public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
