public class CompleteCommand extends Command {

    private boolean isCompleted;
    private int index;

    public CompleteCommand(int index, boolean isCompleted) {
        this.index = index;
        this.isCompleted = isCompleted;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task task = tasks.get(index-1);
        task.setCompleted(isCompleted);
        if (isCompleted) {
            ui.showCompleteTask(task);
        } else {
            ui.showUncompleteTask(task);
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
