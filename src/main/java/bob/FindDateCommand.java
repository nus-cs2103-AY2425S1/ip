package bob;

public class FindDateCommand extends Command {
    protected static String[] params = new String[] { "findDate" };
    protected static int paramCount = 1;
    private final String date;

    public FindDateCommand(String date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] tasksFound = tasks.findTasksOn(this.date);
        String[] toPrint = new String[tasksFound.length + 2];
        toPrint[0] = String.format("Here are the tasks occuring on %s:", this.date);
        toPrint[tasksFound.length + 1] = String.format("Number of tasks found: %d", tasksFound.length);
        System.arraycopy(tasksFound, 0, toPrint, 1, tasksFound.length);

        return Printer.format(toPrint);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindDateCommand temp) {
            return this.date.equals(temp.date);
        }
        return false;
    }
}
