package panorama.command;

import panorama.TaskList;
import panorama.task.Task;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand implements Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_SHORTHAND = "m";

    private TaskList taskList;

    private int id;

    /**
     * Constructs a MarkCommand with the specified task list and task ID.
     *
     * @param taskList The task list containing the task to be marked.
     * @param id       The ID of the task to be marked as completed.
     */
    public MarkCommand(TaskList taskList, int id) {
        this.taskList = taskList;
        this.id = id;
    }

    @Override
    public Response execute() {
        assert taskList != null;
        taskList.mark(id);
        Task task = taskList.get(id);
        return new Response("Nice! I've marked this task as done:\n" + task.toString(), false);
    }
}
