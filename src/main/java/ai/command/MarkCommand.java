package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;
import ai.task.Task;

/**
 * Executes the command that marks the task as complete.
 */
public class MarkCommand extends Command {
    private int i;

    public MarkCommand(String index) {
        i = Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws AiException {
        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist...\n"
                    + "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        temp.mark();
        return "Marked as done... since you have time, how about a drink ;)\n"
                + temp + "\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
