package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;
import ai.exception.IndexOutOfBoundsAiException;
import ai.exception.WrongFormatAiException;
import ai.task.Task;


/**
 * Executes the command that deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int i;

    /**
     * Stores the index of the task to be deleted.
     * @param index Integer number of the task to be deleted.
     * @throws WrongFormatAiException if a non-integer is inputted.
     */
    public DeleteCommand(String index) throws WrongFormatAiException {
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
        tasks.delete(i);

        return "Gotchyaa, task removed!!\n\n"
                + temp + "\n\n"
                + String.format("You have %d tasks in your list :p\n", tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
