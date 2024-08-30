package bob;

public class DeleteCommand  extends Command {
    protected static String[] params = new String[] { "delete" };
    protected static int paramCount = 1;
    protected static String identifier = "delete";
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.idx <= 0 || this.idx > tasks.getSize()) {
            throw new TaskIndexException(String.format("%d", this.idx));
        }
        Task deleted = tasks.delete(idx);
        Printer.prettyPrint(new String[] {"Noted. I've removed this task:",
                deleted.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize())});
    }
}