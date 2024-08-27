public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.markTask(index);
        ui.showCommand("Nice! I've marked this task as done:\n" +
                "\t   " + task);
        storage.save(tasks);
    }
}
