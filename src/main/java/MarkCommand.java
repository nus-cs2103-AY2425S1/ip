import java.io.IOException;

public class MarkCommand extends Command{
    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            Task task = tasks.getTask(this.num);
            if (!task.getStatus()) {
                task.changeStatus();
                String msg = "Nice! I've marked this task as done:\n" +
                        task.toString();
                storage.saveTaskList(tasks);
                ui.printWithLines(msg);
            } else {
                String msg = "This task is already marked done!";
                ui.printWithLines(msg);
            }
        }
    }
}
