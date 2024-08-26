public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.getTask(index);
        tasks.unmarkTask(index);
        ui.showCommand("\t____________________________________________________________\n" +
                "\t Nice! I've marked this task as not done yet:\n" +
                "\t   " + task + "\n" +
                "\t____________________________________________________________");
        storage.save(tasks);
    }
}
