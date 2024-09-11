package chatbaby;

/**
 * Represents a command to delete a task in the ChatBaby application.
 * Extends the Command class to perform the deletion of a specific task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the specified command body.
     *
     * @param commandBody The command body containing the task index to delete.
     */
    public DeleteCommand(String commandBody) {
        super(commandBody);
    }

    /**
     * Executes the delete command by removing a task from the task list.
     *
     * @param tasks The task list from which the task will be removed.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object used to save the updated task list.
     * @throws ChatBabyException If the task index is invalid or cannot be parsed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        assert tasks.size() > 0 : "Task list should have at least one task before removing";
        try {
            int index = Integer.parseInt(commandBody.substring(7).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.deleteTask(index);
                System.out.println("Noted. I've removed this task:\n"
                        + removedTask.toString() + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new ChatBabyException("Oh no!!! The task index is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new ChatBabyException("Oh no!!! The task index is invalid.");
        }
    }
}
