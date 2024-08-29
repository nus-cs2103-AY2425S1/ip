package command;

import task.TaskList;

public class ListTasksCommand extends Command {
    public ListTasksCommand() {
        super(0, null);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.taskListToString();
    }    
}
