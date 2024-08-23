import java.util.List;
import java.util.StringJoiner;

public class CreateDeadlineCommand implements Command {
    private final List<Task> items;
    private final String name;
    private final String by;

    public CreateDeadlineCommand(List<Task> items, String input) {
        this.items = items;


        // Parse the "name" and "by" from the input
        StringJoiner nameSb = new StringJoiner(" ");
        StringJoiner bySb = new StringJoiner(" ");

        // Remove the command from the input
        String inputArgs = input.split(" ", 2)[1];
        String[] split = inputArgs.split(" ");

        StringJoiner currentSb = nameSb;
        for (String s : split) {
            if (s.equals("/by")) {
                currentSb = bySb;
                continue;
            }

            currentSb.add(s);
        }

        this.name = nameSb.toString();
        this.by = bySb.toString();
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
