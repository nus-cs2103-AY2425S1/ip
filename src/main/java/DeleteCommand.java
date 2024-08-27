import java.io.IOException;

public class DeleteCommand extends Command implements Savable {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        Task removedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        return "Noted. I've removed this task:\n  " + removedTask +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTasks(taskList.getTasks());
    }
}
