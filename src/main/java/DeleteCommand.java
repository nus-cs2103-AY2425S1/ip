public class DeleteCommand implements Command {
    private final String taskDetails;

    public DeleteCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int i = Integer.parseInt(taskDetails) - 1;
        ui.showRemoveTaskMessage(taskList.getTask(i));
        taskList.deleteTask(i);
        ui.showNumOfTasksMessage(taskList);
        storage.write(taskList);
    }
}
