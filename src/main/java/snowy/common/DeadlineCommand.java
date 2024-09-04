package snowy.common;

import snowy.tasklist.Deadline;

/**
 * Represents a command to add a task with a deadline.
 *
 * The DeadlineCommand class allows the user to add a task with a specific deadline to the task list.
 * */
public class DeadlineCommand extends Command {
    public static final String DEADLINE = "deadline";
    private final String description;
    private final String date;

    /**
     * Constructs a DeadlineCommand with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param date the due date for the task in the format "yyyy-MM-dd HHmm"
     */
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
        taskList.addTask(deadline);
        return new CommandResult("Added a task with a deadline to your list of tasks");
    }
}

