package bob;

public class DeadlineCommand  extends Command {
    protected static String[] params = new String[] {
            "deadline", "/by"
    };
    protected static int paramCount = 2;
    protected static String identifier = "deadline";
    private String name;
    private String by;

    public DeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = tasks.deadline(this.name, this.by);
        Printer.prettyPrint(new String[] { "Got it. I've added this task:",
                " " + deadline.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize()) });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineCommand temp) {
            return this.name.equals(temp.name) && this.by.equals(temp.by);
        }
        return false;
    }
}
