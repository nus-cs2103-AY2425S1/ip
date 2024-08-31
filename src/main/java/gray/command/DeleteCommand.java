package gray.command;

import gray.GrayException;
import gray.TaskList;
import gray.Ui;
import gray.task.Task;

/**
 * A command that attempts to delete an event.
 */
public class DeleteCommand extends Command {
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
     * @param ui
     * @param tasks
     * @throws GrayException
     */
    @Override
    public void execute(Ui ui, TaskList tasks) throws GrayException {
        if (index <= 0 || index > tasks.size()) {
            throw new GrayException("Not a valid index");
        }
        Task task = tasks.remove(index - 1);
        ui.say(String.format("""
                Noted. I've removed this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size()));
    }
}
