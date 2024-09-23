package rizz.command;
import rizz.source.TaskList;


public class DeleteCommand extends SaveableCommand {
    private final int[] taskIndex;

    public DeleteCommand(int... taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks) {

        return "Noted. I've removed these task:\n" + tasks.deleteTask(taskIndex)
                + "\n\nNow you have " + tasks.getLength() + " tasks in the list.\n" + tasks.toString();
    }
}

