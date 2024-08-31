package bob;

public class ListCommand extends Command {
    protected static String[] params = new String[] { "list" };
    protected static int paramCount = 0;
    protected static String identifier = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] toPrint = new String[tasks.getSize() + 1];
        toPrint[0] = "Here are the tasks in your list:";
        System.arraycopy(tasks.describeTasks(), 0, toPrint, 1, tasks.getSize());
        Printer.prettyPrint(toPrint);
    }
}
