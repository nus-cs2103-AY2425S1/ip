package commands;
import tasks.Task;
import tasks.TaskList;
public class MarkCommand extends Command {

    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.findTask(input);

        String message;

        if (input.contains("unmark")) {
            t.markUndone();
            message = "tasks.Task undone. No worries, I won't judge... much.\n";
        } else {
            t.markDone();
            message = "tasks.Task complete. If I had arms, I might give you a pat on the back.\n";
        }

        return message + t;
    }
}
