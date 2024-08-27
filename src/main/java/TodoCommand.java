import java.io.IOException;

public class TodoCommand extends Command implements Savable {
    private final Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public String execute() {
        taskList.addTask(todo);
        return "Got it. I've added this task:\n  " + todo +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTask(todo);
    }
}
