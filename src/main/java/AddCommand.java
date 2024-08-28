import java.io.IOException;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAddTask(tasks.addTask(this.task), tasks.getSize());
    }
}
