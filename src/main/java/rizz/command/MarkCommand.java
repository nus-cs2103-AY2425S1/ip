package rizz.command;
import rizz.source.TaskList;
import rizz.task.Task;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    } //cause 2nd item of list is Arr[1]

    @Override
    public String execute(TaskList tasks) {
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

}
