public class MarkCommand extends Command {
    private int taskIndex;

    // Note that when user does "mark 1" => the 1 is a string
    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        Task task = tasks.getTask(taskIndex - 1); // since tasks are 0-indexed
        task.markAsDone();
        ui.showLine();
        ui.showMessage("Nice! I've marked this task as done:\n" + task.toString());
        ui.showLine();
        // Update storage
        storage.save(tasks.getTasks());
    }
    
}
