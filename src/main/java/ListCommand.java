public class ListCommand extends CommandBase {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.getAllTasks());
    }
}