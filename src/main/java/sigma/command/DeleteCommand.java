package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;
import sigma.task.Task;

/**
 * Represents the command to delete a task from the list.
 */
public class DeleteCommand extends Commands {

    public DeleteCommand(String[] split) {
        super(split);
    }

    /**
     * Deletes a task from the task list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        if (split.length < 2) {
            throw new SigmaException("What the sigma? You're missing the task! " +
                    "Write \"delete <task>\"!");
        }
        int index = Integer.parseInt(split[1]);
        if (index > 0 && index <= tasks.size()) {
            Task item = tasks.get(index - 1);
            tasks.remove(index - 1);
            ui.print(String.format("Dang, I'm going to delete this for you:\n %s\n " +
                    "Now you have %d tasks!", item.toString(), tasks.size()));
        } else {
            throw new SigmaException("What the skibidi? Invalid task number!");
        }
    }
}
