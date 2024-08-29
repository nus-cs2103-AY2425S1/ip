import java.io.IOException;

public class AddCommand extends Command{
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        tasks.addTask(this.task);
        storage.saveTaskList(tasks);
        ui.printWithLines(" Got it. I've added this task:\n  " + this.task + tasks.listLength());
    }
}
