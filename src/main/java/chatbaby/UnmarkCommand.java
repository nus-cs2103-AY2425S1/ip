package chatbaby;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an {@code UnmarkCommand} object with the given command body.
     *
     * @param commandBody The body of the command.
     */
    public UnmarkCommand(String commandBody) {
        super(commandBody);
    }

    /**
     * Executes the command to unmark the task as not done in the task list.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     * @throws ChatBabyException If the task index is invalid or not a number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            int index = Integer.parseInt(commandBody.substring(7).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.taskAt(index).unMarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + tasks.taskAt(index).toString());
            } else {
                throw new ChatBabyException("Oh no!!! The task index is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new ChatBabyException("Oh no!!! The task index is invalid.");
        }
    }
}
