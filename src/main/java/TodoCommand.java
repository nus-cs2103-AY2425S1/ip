import java.io.IOException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Todo t = tasks.addTodo(this.description);
        storage.appendToFile(t.toFile());
        Ui.printAddTodo(tasks);
    }
}
