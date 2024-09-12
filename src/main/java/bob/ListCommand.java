package bob;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves all tasks and prints them to the user when run.
 */
public class ListCommand extends Command {
    protected static String[] params = new String[] {
        "list"
    };
    protected static int paramCount = 0;

    /**
     * Executes the list command by retrieving and printing all tasks in the {@code TaskList}.
     * This method formats the tasks and displays them to the user.
     *
     * @param tasks the {@code TaskList} containing all tasks to be listed.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} where tasks are saved.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] toPrint = new String[tasks.getSize() + 1];
        toPrint[0] = "Here are the tasks in your list:";
        System.arraycopy(tasks.describeTasks(), 0, toPrint, 1, tasks.getSize());
        return Printer.format(toPrint);
    }
}
