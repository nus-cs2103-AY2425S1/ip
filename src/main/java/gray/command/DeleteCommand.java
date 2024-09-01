package gray.command;

import gray.GrayException;
import gray.Tasks;
import gray.task.Task;

/**
 * A command that attempts to delete an event.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a command to delete task.
     *
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion of task.
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
        Task task = tasks.remove(index - 1);
        return String.format("""
                Noted. I've removed this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size());
    }
}
