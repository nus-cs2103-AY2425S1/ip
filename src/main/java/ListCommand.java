import java.util.List;

public class ListCommand implements Command {
    private final List<Task> items;

    public ListCommand(List<Task> items) {
        this.items = items;
    }

    @Override
    public void execute() {
        for (int i = 0; i < items.size(); i++) {
            Task task = items.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
}
