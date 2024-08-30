public class ListCommand extends Command {
    protected static String[] params = new String[] { "list" };
    protected static int paramCount = 0;
    protected static String identifier = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Printer.prettyPrint(tasks.describeTasks());
    }
}
