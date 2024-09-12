package chatbaby;

/**
 * Represents a command to mark a task as done.
 * This class handles the marking of a task at a specified index as completed.
 */
public class MarkCommand extends Command {
    private static final int BEGIN_INDEX = 5;

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
     * Parses the index from the command body, validates it, and updates the task's status as done.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui object for user interactions.
     * @param storage The Storage object for saving and loading tasks.
     * @throws ChatBabyException If the task index is invalid or cannot be parsed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            int index = Integer.parseInt(commandBody.substring(BEGIN_INDEX).trim()) - 1;
            if (index < 0 && index > tasks.size()) {
                throw new ChatBabyException("Oh no!!! The task index is invalid.");
            }
            tasks.getTaskAt(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + tasks.getTaskAt(index).toString());
        } catch (NumberFormatException e) {
            throw new ChatBabyException("Oh no!!! The task index is invalid.");
        }
    }
}
