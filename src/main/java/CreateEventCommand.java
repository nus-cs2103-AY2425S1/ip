import java.util.List;
import java.util.StringJoiner;

public class CreateEventCommand implements Command {
    private final List<Task> items;
    private final String name;
    private final String from;
    private final String to;

    public CreateEventCommand(List<Task> items, String input) {
        this.items = items;

        // Parse the "name", "from" and "to" from the input
        StringJoiner nameSb = new StringJoiner(" ");
        StringJoiner fromSb = new StringJoiner(" ");
        StringJoiner toSb = new StringJoiner(" ");

        // Remove the command from the input
        String inputArgs = input.split(" ", 2)[1];
        String[] split = inputArgs.split(" ");

        StringJoiner currentSb = nameSb;
        for (String s : split) {
            if (s.equals("/from")) {
                currentSb = fromSb;
                continue;
            } else if (s.equals("/to")) {
                currentSb = toSb;
                continue;
            }

            currentSb.add(s);
        }

        this.name = nameSb.toString();
        this.from = fromSb.toString();
        this.to = toSb.toString();
    }

    @Override
    public void execute() {
        Task task = new Event(this.name, this.from, this.to);
        items.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.items.size());
    }
}
