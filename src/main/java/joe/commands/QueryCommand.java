package joe.commands;

import joe.tasks.TaskList;

public class QueryCommand extends Command {
    private final TaskList tasks;

    private final String date;

    public QueryCommand(TaskList tasks, String date) {
        this.tasks = tasks;
        this.date = date;
    }

    @Override
    public void execute() {
        tasks.queryTasksByDate(date);
    }
}
