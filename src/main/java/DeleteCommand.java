public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }
    public void execute(int index, Storage storage, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        ui.removeTask(task);
    }
}
