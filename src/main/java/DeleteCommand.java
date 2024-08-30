public class DeleteCommand implements Command {
    private final int index;

    DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        Task task = tasks.delete(index);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "Task removed: \n" +
                task.toString().indent(2) +
                + tasks.size() + plural + " in list\n";
        ui.showMessage(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
