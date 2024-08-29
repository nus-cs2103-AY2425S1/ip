public class IncompleteCommand extends Command {

    public IncompleteCommand(String command) {
        super(command);
    }
    public void execute(Storage storage, int index, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        task.markIncomplete();
        ui.incompleteTask(task);
    }
}
