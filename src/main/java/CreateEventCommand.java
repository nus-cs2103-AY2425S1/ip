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
        StringJoiner nameSj = new StringJoiner(" ");
        StringJoiner fromSj = new StringJoiner(" ");
        StringJoiner toSj = new StringJoiner(" ");

        // Remove the command from the input
        String inputArgs = input.split(" ", 2)[1];
        String[] split = inputArgs.split(" ");

        StringJoiner currentSj = nameSj;
        for (String s : split) {
            if (s.equals("/from")) {
                currentSj = fromSj;
                continue;
            } else if (s.equals("/to")) {
                currentSj = toSj;
                continue;
            }

            currentSj.add(s);
        }

        this.name = nameSj.toString();
        this.from = fromSj.toString();
        this.to = toSj.toString();
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
