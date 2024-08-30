package chatbaby;

/**
 * Represents a command to mark a task as done.
 * This class handles the marking of a task at a specified index as completed.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand with the specified command body.
     *
     * @param commandBody The command string input by the user.
     */
    public MarkCommand(String commandBody) {
        super(commandBody);
    }

    /**
     * Executes the command to mark a task as done.
     * Parses the index from the command body, validates it, and updates the task's status to done.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui object for user interactions.
     * @param storage The Storage object for saving and loading tasks.
     * @throws ChatBabyException If the task index is invalid or cannot be parsed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            int index = Integer.parseInt(commandBody.substring(5).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.taskAt(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n"
                        + tasks.taskAt(index).toString());
            } else {
                throw new ChatBabyException("Oh no!!! The task index is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new ChatBabyException("Oh no!!! The task index is invalid.");
        }
    }
}
