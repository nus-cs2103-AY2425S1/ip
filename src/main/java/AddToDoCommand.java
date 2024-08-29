import java.io.IOException;

public class AddToDoCommand implements Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = new ToDos(description);
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
