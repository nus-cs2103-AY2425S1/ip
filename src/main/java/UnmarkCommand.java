import java.util.List;

public class UnmarkCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public UnmarkCommand(List<Task> items, String input) {
        String inputArgs = Parser.parseInput(input).args();
        int index = Integer.parseInt(inputArgs) - 1;

        this.items = items;
        this.itemIndex = index;
    }

    @Override
    public void execute() {
        Task task = items.get(itemIndex);
        task.unmarkDone();
        System.out.printf("Marked this task as not yet done:\n%s\n", task);
    }
}
