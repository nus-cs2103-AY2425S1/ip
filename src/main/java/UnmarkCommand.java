public class UnmarkCommand extends Command {
    private TaskList taskList;
    private int taskNumber;
    public UnmarkCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() {
        taskList.unmarkTask(taskNumber);
    }
}