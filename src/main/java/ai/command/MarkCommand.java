package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;
import ai.exception.IndexOutOfBoundsAiException;
import ai.exception.WrongFormatAiException;
import ai.task.Task;

/**
 * Executes the command that marks the task as complete.
 */
public class MarkCommand extends Command {
    private int i;

    /**
     * Stores the index of the task to be marked.
     * @param index Integer number of the task to be marked.
     * @throws WrongFormatAiException if a non-integer is inputted.
     */
    public MarkCommand(String index) throws WrongFormatAiException {
        try {
            i = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new WrongFormatAiException("You tried to type a non-number...", "delete 5");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws AiException {
        if (i < 0 || i >= tasks.size()) {
            throw new IndexOutOfBoundsAiException(tasks.size());
        }

        Task temp = tasks.get(i);
        temp.mark();
        return "Marked as done...\n\n"
                + temp + "\n\n"
                + "Since you have time, how about a drink ;)\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
