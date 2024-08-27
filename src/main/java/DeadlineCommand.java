public class DeadlineCommand extends Command {
    private Deadline deadlineTask;
    public DeadlineCommand(Deadline deadlineTask) {
        this.deadlineTask = deadlineTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
            Commands.addTask(taskList, deadlineTask);
            ui.addTaskMessage(taskList, deadlineTask);
            storage.saveTasks(taskList);
    }
}
