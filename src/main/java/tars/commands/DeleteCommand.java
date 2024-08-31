package tars.commands;

import tars.tasks.Task;
import tars.tasks.TaskList;

public class DeleteCommand extends Command {

    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.deleteTask(input);

        return String.format("""
                Wow you're freeing yourself up
                   %s
                You now have %s tasks left""", t, tasks.noOfTasks());

    }
}
