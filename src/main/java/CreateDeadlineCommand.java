import java.util.List;
import java.util.StringJoiner;

public class CreateDeadlineCommand implements Command {
    private final List<Task> items;
    private final String name;
    private final String by;

    public CreateDeadlineCommand(List<Task> items, String input) {
        this.items = items;

        // Remove the command from the input
        String inputArgs = input.split(" ", 2)[1];
        String[] args = Parser.extractArgs(inputArgs, new String[] { "/by" });

        this.name = args[0];
        this.by = args[1];
    }

    @Override
    public void execute() {
        Task task = new Deadline(this.name, this.by);
        items.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.items.size());
    }
}
