public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String index) throws ChatBuddyException {
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
        Task removedTask = tasks.getTask(index);
        tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showDeleteTask(removedTask, tasks.size());
    }
}
