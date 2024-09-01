public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(String index) throws ChatBuddyException {
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        if (index >= tasks.size() || index < 0) {
            throw new ChatBuddyException("Task number out of range.");
        }
        Task task = tasks.getTask(index);
        task.unmarkAsDone();
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkTask(task);
    }
}
