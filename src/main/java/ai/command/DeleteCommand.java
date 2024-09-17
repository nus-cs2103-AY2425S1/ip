package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;
import ai.task.Task;

/**
 * Executes the command that deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int i;

    public DeleteCommand(String index) {
        i = Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws AiException {
        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist... can't be removed >....<\n"
                    + "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
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
