public class DeleteCommand extends Command{

    private final int index;
    public DeleteCommand(int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList taskList) {
        Task delTask = taskList.removeTaskAtIndex(this.index);
        UI.printDeleteTask(delTask);
        UI.printCurrentTaskListStatus(taskList);
    }
}
