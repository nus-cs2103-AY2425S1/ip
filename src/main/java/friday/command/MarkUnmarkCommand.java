package friday.command;

import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

/**
 * Represents a command to mark or unmark a task as done or not done.
 */
public class MarkUnmarkCommand extends Command {
    private final String[] inputs;

    /**
     * Constructs a MarkUnmarkCommand with the specified input.
     *
     * @param inputs The input array containing the command type ('mark' or 'unmark') and the task index.
     */
    public MarkUnmarkCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Executes the mark/unmark command, updating the status of the specified task.
     *
     * @param tasks   The task list to be modified by the command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list.
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (!inputs[1].chars().allMatch(Character::isDigit))
            throw new FridayException("\tInvalid input. Where would you like to " + inputs[0] + "?");
        int index = Integer.parseInt(inputs[1]);
        if (index > tasks.getSize() || index <= 0)
            throw new FridayException("\tInvalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        Task task = tasks.getTasks().get(index - 1);
        if (inputs[0].equals("mark")) {
            task.markAsDone();
            System.out.println("\tNice! I've marked this task as done:\n\t  " + task);
        } else {
            task.unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:\n\t  " + task);
        }
        storage.saveTasks(tasks.getTasks());
    }
}
