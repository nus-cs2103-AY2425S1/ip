package rizz.command;
import rizz.source.TaskList;


public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {
        String deletedTask = tasks.deleteTask(index - 1);

        return "Noted. I've removed this task:\n" + deletedTask.toString()
                + "Now you have %d tasks in the list.\n" + tasks.toString();
    }
}

