package snowy.common;

import snowy.tasklist.Deadline;

public class DeadlineCommand extends Command {
    public static final String DEADLINE = "deadline";
    private final String description;
    private final String date;

    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    @Override
    public CommandResult execute() {
        Deadline deadline = new Deadline(description, date);
        taskList.addTask(deadline);
        return new CommandResult("Added a task with a deadline to your list of tasks");
    }
}

