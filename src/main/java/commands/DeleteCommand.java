package commands;
import tasks.Task;
import tasks.TaskList;

public class DeleteCommand extends Command {

    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.deleteTask(input);

        return String.format("Wow you're freeing yourself up\n   %s\nYou now have %s tasks left", t, tasks.noOfTasks());

    }
}
