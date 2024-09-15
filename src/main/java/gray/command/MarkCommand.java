package gray.command;

import gray.GrayException;
import gray.Tasks;
import gray.task.Task;

/**
 * A command that marks or un-marks a task as completed.
 */
public class MarkCommand implements Command {

    private final String command;
    private final int index;

    /**
     * Constructs a command to mark or un-mark a task.
     *
     * @param command
     * @param index
     */
    public MarkCommand(String command, int index) {
        this.command = command;
        this.index = index;
    }

    /**
     * Executes mark or un-mark task.
     *
     * @param tasks
     * @return
     * @throws GrayException
     */
    @Override
    public String execute(Tasks tasks) throws GrayException {
        if (index <= 0 || index > tasks.size()) {
            throw new GrayException("Not a valid index");
        }
        Task task = tasks.get(index - 1);
        if (command.equals("mark")) {
            task.mark();
            tasks.save();
            return String.format(
                    "Nice! I've marked this task as done\n\t%s",
                    task);
        } else {
            task.unmark();
            tasks.save();
            return String.format(
                    "Ok, I've unmarked this task as not done yet:\n\t%s",
                    task);
        }
    }
}
