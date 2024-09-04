import elara.task.TaskList;

public class UnmarkCommand implements Command {
    private final String taskDetails;

    public UnmarkCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int i = Integer.parseInt(taskDetails) - 1;
        taskList.unmarkTask(i);
        ui.showUnmarkedTaskMessage(taskList.getTask(i));
        storage.write(taskList);
    }
}
