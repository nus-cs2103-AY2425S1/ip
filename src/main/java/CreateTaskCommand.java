import java.util.List;

public class CreateTaskCommand implements Command {
    private final List<Task> items;
    private final String name;

    public CreateTaskCommand(List<Task> items, String name) {
        this.items = items;
        this.name = name;
    }

    @Override
    public void execute() {
        items.add(new Task(name));
    }
}
