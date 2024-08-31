package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;
import ai.task.Task;

/**
 * Executes the command that unmarks the task.
 */
public class UnmarkCommand extends Command {
    private int i;

    public UnmarkCommand(String index) {
        i = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws AiException {
        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist...\n"
                    + "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        temp.unmark();
        System.out.println("Donzo, task unmarked! Let me know if you need anything else :3");
        System.out.println(temp + "\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
