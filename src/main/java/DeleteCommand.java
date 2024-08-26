public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.getTask(index - 1);
        tasks.deleteTask(index);
        ui.showCommand("\t____________________________________________________________\n" +
                "\t Noted. I've removed this task:\n" +
                "\t   " + task + "\n" +
                "\t Now you have " + tasks.getSize() + " tasks" + " in the list.\n" +
                "\t____________________________________________________________");
    }
}
