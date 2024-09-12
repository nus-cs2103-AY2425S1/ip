package bob;

/**
 * Represents a command to find tasks with specific text in its name.
 * This command searches for tasks with a specific text and prints the results when run.
 */
public class FindCommand extends Command {
    protected static String[] params = new String[] {
        "find"
    };
    protected static int paramCount = 1;
    private final String text;

    /**
     * Constructs a {@code FindCommand} with the specified text.
     *
     * @param text the text to search in the names of tasks for.
     */
    public FindCommand(String text) {
        this.text = text;
    }

    /**
     * Executes the find command by searching for tasks with names containing the specified text.
     * This method retrieves tasks from the {@code TaskList} that match the text and prints the results.
     *
     * @param tasks the {@code TaskList} containing all tasks.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} where tasks are saved.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] tasksFound = tasks.findTasksWith(this.text);
        String[] toPrint = new String[tasksFound.length + 2];
        toPrint[0] = String.format("Here are the tasks matching %s:", this.text);
        toPrint[tasksFound.length + 1] = String.format("Number of tasks found: %d", tasksFound.length);
        System.arraycopy(tasksFound, 0, toPrint, 1, tasksFound.length);

        return Printer.format(toPrint);
    }

    /**
     * Checks if this {@code FindCommand} is equal to another object.
     * Two {@code FindCommand} instances are considered equal if they have the same text.
     *
     * @param obj the object to compare this command with.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand temp) {
            return this.text.equals(temp.text);
        }
        return false;
    }
}
