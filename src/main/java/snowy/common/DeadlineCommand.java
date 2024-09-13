package snowy.common;

import snowy.tasklist.Deadline;

/**
 * Represents a command to add a task with a deadline.
 *
 * The DeadlineCommand class allows the user to add a task with a specific deadline to the task list.
 * */
public class DeadlineCommand extends Command {

    private final String description;
    private final String date;

    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @return a CommandResult indicating the task has been added
     */
    @Override
    public CommandResult execute() {
        Deadline deadline = new Deadline(description, date);
        String str = taskList.addTask(deadline);
        return new CommandResult(str + "\nAdded a task with a deadline to your list of tasks");
    }
}
