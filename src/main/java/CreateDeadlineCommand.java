import java.util.List;
import java.util.StringJoiner;

public class CreateDeadlineCommand implements Command {
    private final List<Task> items;
    private final String name;
    private final String by;

    public CreateDeadlineCommand(List<Task> items, String input) {
        this.items = items;


        // Parse the "name" and "by" from the input
        StringJoiner nameSj = new StringJoiner(" ");
        StringJoiner bySj = new StringJoiner(" ");

        // Remove the command from the input
        String inputArgs = input.split(" ", 2)[1];
        String[] split = inputArgs.split(" ");

        StringJoiner currentSj = nameSj;
        for (String s : split) {
            if (s.equals("/by")) {
                currentSj = bySj;
                continue;
            }

            currentSj.add(s);
        }

        this.name = nameSj.toString();
        this.by = bySj.toString();
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
