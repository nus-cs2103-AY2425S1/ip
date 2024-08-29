public class AddCommand extends Command {
    private Task task;

    public AddCommand(Deadline deadline) {
        super(false);
        this.task = deadline;
    }

    public AddCommand(Todo todo) {
        super(false);
        this.task = todo;
    }

    public AddCommand(Event event) {
        super(false);
        this.task = event;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException {
        taskList.addTask(this.task);
        ui.PixelSays("Got it. I've added this task:", "  " + this.task,
                "Now you have " + taskList.size() + " tasks in the list.");
    }
}
