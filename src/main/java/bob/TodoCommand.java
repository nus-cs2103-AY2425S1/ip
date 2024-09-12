package bob;

/**
 * This command creates a ToDo task with the specified name and adds it to the task list when run.
 */
public class TodoCommand extends Command {
    protected static String[] params = new String[] {
        "todo"
    };
    protected static int paramCount = 1;
    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = tasks.todo(name);
        return Printer.format(new String[] { "Got it. I've added this task:",
            " " + todo.toString(),
            String.format("Now you have %d tasks in the list.", tasks.getSize()) });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCommand temp) {
            return this.name.equals(temp.name);
        }
        return false;
    }
}
