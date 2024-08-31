package bob;

public class EventCommand  extends Command {
    protected static String[] params = new String[] { "event", "/from", "/to" };
    protected static int paramCount = 3;
    protected static String identifier = "event";
    private String name;
    private String from;
    private String to;

    public EventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = tasks.event(this.name, this.from, this.to);
        Printer.prettyPrint(new String[] { "Got it. I've added this task:",
                " " + event.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize()) });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand temp) {
            return this.name.equals(temp.name) && this.from.equals(temp.from) && this.to.equals(temp.to);
        }
        return false;
    }
}
