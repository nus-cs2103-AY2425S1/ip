package panorama.command;

import java.time.LocalDate;

import panorama.TaskList;
import panorama.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_SHORTHAND = "d";

    private TaskList taskList;

    private String name;
    private LocalDate date;

    /**
     * Constructs a DeadlineCommand with the specified task list, task name, and deadline date.
     *
     * @param taskList The task list to which the deadline task will be added.
     * @param name     The name of the deadline task.
     * @param date     The due date of the deadline task.
     */
    public DeadlineCommand(TaskList taskList, String name, LocalDate date) {
        this.taskList = taskList;
        this.name = name;
        this.date = date;
    }

    @Override
    public Response execute() {
        assert taskList != null;
        Deadline deadline = new Deadline(name, date);
        taskList.add(deadline);
        return new Response("Added task:\n" + deadline.toString(), false);
    }
}
