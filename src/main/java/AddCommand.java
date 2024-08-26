public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showCommand("\t____________________________________________________________\n" +
                "\t Got it. I've added this task:\n" +
                "\t   " + task.toString() + "\n" +
                "\t Now you have " + tasks.getSize() + " task" + (tasks.getSize() > 1 ? "s" : "") + " in the list.\n" +
                "\t____________________________________________________________");
    }
}
