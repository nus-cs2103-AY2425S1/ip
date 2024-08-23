import java.util.List;

public class MarkCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public MarkCommand(List<Task> items, String input) {
        String[] split = input.split(" ");
        int index = Integer.parseInt(split[1]) - 1;

        this.items = items;
        this.itemIndex = index;
    }

    @Override
    public void execute() {
        Task task = items.get(itemIndex);
        task.markDone();
        System.out.printf("Marked this task as done:\n%s\n", task);
    }
}
