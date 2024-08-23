import java.util.List;

public class MarkCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public MarkCommand(List<Task> items, int itemIndex) {
        this.items = items;
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task task = items.get(itemIndex);
        task.markDone();
        System.out.printf("Marked this task as done:\n%s\n", task);
    }
}
