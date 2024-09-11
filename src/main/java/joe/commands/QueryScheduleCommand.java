package joe.commands;

import joe.tasks.TaskList;

/**
 * Represents a command to query tasks by date from the task list.
 */
public class QueryScheduleCommand extends Command {
    private final TaskList tasks;

    private final String date;

    /**
     * Constructs a QueryScheduleCommand object.
     * @param tasks Task list to query tasks from.
     * @param date Date to query tasks by.
     */
    public QueryScheduleCommand(TaskList tasks, String date) {
        this.tasks = tasks;
        this.date = date;
    }

    @Override
    public String execute() {
        return tasks.viewScheduleOnDate(date);
    }
}
