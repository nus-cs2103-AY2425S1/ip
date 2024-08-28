package ollie;

public class AddCommand extends Command {
    Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.getSize());
    }
}
