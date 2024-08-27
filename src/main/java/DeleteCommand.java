public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.deleteTask(index);
        ui.showCommand("Noted. I've removed this task:\n" +
                "\t   " + task + "\n" +
                "\t Now you have " + tasks.getSize() + " tasks" + " in the list.");
        storage.save(tasks);
    }
}
