package bob;

public class DeadlineCommand  extends Command {
    protected static String[] params = new String[] { "deadline" };
    protected static int paramCount = 2;
    protected static String identifier = "deadline";
    private String name;
    private String by;

    public DeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = tasks.deadline(this.name, this.by);
        Printer.prettyPrint(new String[] { "Got it. I've added this task:",
                " " + deadline.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize()) });
    }
}
