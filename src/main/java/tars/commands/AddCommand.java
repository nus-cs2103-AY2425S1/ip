package tars.commands;

import tars.tasks.Task;
import tars.tasks.TaskList;

public class AddCommand extends Command {

    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.addTask(input);

        return String.format("""
                Added yet another task
                   %s
                You now have %d tasks. Are you gonna do any of them?""",
                t, tasks.noOfTasks());

    }
}
