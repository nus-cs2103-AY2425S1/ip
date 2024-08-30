package bob;

public class MarkCommand  extends Command {
    protected static String[] params = new String[] { "mark" };
    protected static int paramCount = 1;
    protected static String identifier = "mark";
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(idx);
        Printer.prettyPrint(new String[] {
                "Nice! I've marked this task as done:",
                tasks.describeTask(idx) });
    }
}