package twilight;

import java.io.IOException;

/**
 * Processes the deletion of tasks from list.
 */
public class DeleteCommand extends Command {
    protected int taskNum;

    /**
     * Instantiates DeleteCommand.
     *
     * @param details Number of task to be deleted.
     */
    public DeleteCommand(String details) throws InvalidInputException {
        try {
            this.taskNum = Integer.valueOf(details) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Delete command must be followed by a task number from the list.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        String output = tasks.delete(taskNum);
        try {
            storage.saveData(tasks.getTasks());
            return output;
        } catch (IOException e) {
            return "error saving";
        }
    }
}
