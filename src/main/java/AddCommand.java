import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(String title) {
        task = new ToDo(title);
    }

    public AddCommand(String title, String deadline) {
        task = new Deadline(title, deadline);
    }

    public AddCommand(String title, String from, String to) {
        task = new Event(title, from, to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.addTask(task);
        ui.showAddMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
