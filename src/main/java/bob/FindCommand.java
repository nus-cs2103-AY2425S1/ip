package bob;

/**
 * Represents a command to find tasks occurring on a specific date.
 * This command searches for tasks with a specific date and prints the results when run.
 */
public class FindCommand extends Command {
    protected static String[] params = new String[] {
            "find"
    };
    protected static int paramCount = 1;
    protected static String identifier = "find";
    private final String date;

    /**
     * Constructs a {@code FindCommand} with the specified date.
     *
     * @param date the date to search for tasks occurring on.
     */
    public FindCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the find command by searching for tasks occurring on the specified date.
     * This method retrieves tasks from the {@code TaskList} that match the date and prints the results.
     *
     * @param tasks the {@code TaskList} containing all tasks.
     * @param ui the {@code Ui} to interact with the user (not used in this method).
     * @param storage the {@code Storage} where tasks are saved (not used in this method).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] tasksFound = tasks.findTasksOn(this.date);
        String[] toPrint = new String[tasksFound.length + 2];
        toPrint[0] = String.format("Here are the tasks occuring on %s:", this.date);
        toPrint[tasksFound.length + 1] = String.format("Number of tasks found: %d", tasksFound.length);
        System.arraycopy(tasksFound, 0, toPrint, 1, tasksFound.length);

        Printer.prettyPrint(toPrint);
    }

    /**
     * Checks if this {@code FindCommand} is equal to another object.
     * Two {@code FindCommand} instances are considered equal if they have the same date.
     *
     * @param obj the object to compare this command with.
     * @return {@code true} if the object is a {@code FindCommand} with the same date; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand temp) {
            return this.date.equals(temp.date);
        }
        return false;
    }
}