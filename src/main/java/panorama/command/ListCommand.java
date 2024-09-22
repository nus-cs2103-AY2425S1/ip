package panorama.command;

import panorama.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_SHORTHAND = "l";

    private TaskList taskList;

    /**
     * Constructs a ListCommand with the specified task list.
     *
     * @param taskList The task list to be listed.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public Response execute() {
        assert taskList != null;
        return new Response(taskList.toString(), false);
    }
}
