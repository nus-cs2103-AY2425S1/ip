package bob;

public class UnmarkCommand  extends Command {
    protected static String[] params = new String[] {
            "unmark"
    };
    protected static int paramCount = 1;
    protected static String identifier = "unmark";
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkCommand temp) {
            return this.idx == temp.idx;
        }
        return false;
    }
}