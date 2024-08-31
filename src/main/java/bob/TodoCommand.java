package bob;

public class TodoCommand  extends Command {
    protected static String[] params = new String[] {
            "todo"
    };
    protected static int paramCount = 1;
    protected static String identifier = "todo";
    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = tasks.todo(name);
        Printer.prettyPrint(new String[] { "Got it. I've added this task:",
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
