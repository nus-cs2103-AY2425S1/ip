import java.util.List;

public class DeleteCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public DeleteCommand(List<Task> items, String input) {
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
        items.remove(itemIndex);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.items.size());
    }
}
