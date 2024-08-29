public class DeadlineCommand extends TaskCommand {
    public DeadlineCommand(String command) {
        super(command);
    }

    public void execute(Task task, Storage storage, Ui ui) {
        TaskList taskList = storage.getTaskList();
        taskList.add(task);
        ui.addTask(task);

    }


}
