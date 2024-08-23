import java.util.List;

public class MarkCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public MarkCommand(List<Task> items, String input) {
        String inputArgs = Parser.parseInput(input).args();
        int index = Integer.parseInt(inputArgs) - 1;

        if (index < 0 || index >= items.size()) {
            throw new InvalidIndexException(items.size(), index);
        }

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
