package panorama.command;

import panorama.TaskList;
import panorama.task.Task;

/**
 * Represents a command to mark a task as not completed in the task list.
 */
public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_SHORTHAND = "u";

    private TaskList taskList;

    private int id;

    /**
     * Constructs a UnmarkCommand with the specified task list and task ID.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param id       The ID of the task to be marked as not completed.
     */
    public UnmarkCommand(TaskList taskList, int id) {
        this.taskList = taskList;
        this.id = id;
    }

    @Override
    public Response execute() {
        assert taskList != null;
        taskList.unmark(id);
        Task task = taskList.get(id);
        return new Response("OK, I've marked this task as not done yet:\n" + task.toString(), false);
    }
}
