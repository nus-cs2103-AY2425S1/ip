public class CompleteCommand extends Command {

    public CompleteCommand(String command) {
        super(command);
    }
    public void execute(Storage storage, int index, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        task.markCompleted();
        ui.completeTask(task);
    }
}
