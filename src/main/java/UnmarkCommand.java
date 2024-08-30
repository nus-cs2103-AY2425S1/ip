public class UnmarkCommand  extends Command {
    protected static String[] params = new String[] { "mark" };
    protected static int paramCount = 1;
    protected static String identifier = "mark";
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(idx);
        Printer.prettyPrint(new String[] {
                "OK, I've marked this task as not done yet:",
                tasks.describeTask(idx) });
    }
}