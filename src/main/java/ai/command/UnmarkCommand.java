package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;
import ai.exception.WrongFormatAiException;
import ai.task.Task;

/**
 * Executes the command that unmarks the task.
 */
public class UnmarkCommand extends Command {
    private int i;

    /**
     * Stores the index of the task to be unmarked.
     * @param index Integer number of the task to be unmarked.
     * @throws WrongFormatAiException if a non-integer is inputted.
     */
    public UnmarkCommand(String index) throws WrongFormatAiException {
        try {
            i = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new WrongFormatAiException("You tried to type a non-number...", "delete 5");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws AiException {
        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist...\n"
                    + "You might wanna try a valid positive integer till " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        temp.unmark();
        return "Donzo, task unmarked!\n\n"
                + temp + "\n\n"
                + "Let me know if you need anything else :3\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
